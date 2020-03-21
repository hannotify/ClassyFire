package com.github.hannotify.classyfire.ui.statemachine.states

import com.github.hannotify.classyfire.data.category.CategoryType
import com.github.hannotify.classyfire.data.transaction.Transaction
import com.github.hannotify.classyfire.ui.statemachine.State
import com.github.hannotify.classyfire.ui.statemachine.StateContext

abstract class ProcessTransactionsState : State {
    private val supportedExitCommands = arrayOf("exit", "quit")

    protected fun processTransactions(categoryType: CategoryType, stateContext: StateContext): Boolean {
        stateContext.clearScreen()

        val transactions = stateContext.transactionRepository.findTransactionsByCategoryType(categoryType)
        transactions.forEachIndexed { index, transaction ->
            if (stateContext.processedTransactions.contains(transaction)) {
                return@forEachIndexed
            }

            stateContext.printCategoriesByCategoryType(categoryType)
            transaction.print(index, transactions.size)
            val classification = stateContext.classify(transaction)
            var guessedCategoryId: Int? = null

            if (classification != null) {
                guessedCategoryId = stateContext.categories.indexOf(classification.category)
                println("My guess is: '$guessedCategoryId - ${classification.category}' (${classification.probability}%).")
            }

            var userInput: String
            var enteredCategoryId: Int?
            do {
                print("Enter category")

                if (guessedCategoryId != null) {
                    print(" (press enter to use '$guessedCategoryId')")
                }

                print(": ")
                userInput = readLine().toString()
                enteredCategoryId = userInput.toIntOrNull()

            } while (!isUserInputValid(userInput, enteredCategoryId, stateContext.categories.size))

            if (!processUserInput(userInput, transaction, guessedCategoryId, enteredCategoryId, stateContext)) {
                return false
            }

            stateContext.clearScreen()
        }
        return true
    }

    private fun isUserInputValid(userInput: String, enteredCategoryId: Int?, categoryCount: Int): Boolean {
        return userInput.isBlank() || supportedExitCommands.contains(userInput) ||
                (enteredCategoryId != null && enteredCategoryId >= 0 && enteredCategoryId < categoryCount)
    }

    private fun processUserInput(userInput: String, transaction: Transaction, guessedCategoryId: Int?,
                                 enteredCategoryId: Int?, stateContext: StateContext): Boolean {

        if (supportedExitCommands.contains(userInput)) {
            return false
        }

        // haal categorie op o.b.v. nummer
        val categoryId = enteredCategoryId ?: guessedCategoryId
        val chosenCategory = stateContext.categories[categoryId!!]

        // sla op als trainingsdata
        stateContext.classificationService.saveAndLearn(chosenCategory, transaction)

        return true
    }
}
