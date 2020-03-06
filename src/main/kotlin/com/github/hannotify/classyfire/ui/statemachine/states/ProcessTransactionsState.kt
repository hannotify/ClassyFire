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
            stateContext.printCategoriesByCategoryType(categoryType)
            transaction.print(index, transactions.size)

            var userInput: String
            var categoryId: Int?
            do {
                print("Enter category: ")
                userInput = readLine().toString()
                categoryId = userInput.toIntOrNull()
            } while (!isUserInputValid(userInput, categoryId, stateContext.categories.size))

            if (!processUserInput(userInput, transaction, categoryId, stateContext)) {
                return false
            }

            stateContext.clearScreen()
            println("User entered $userInput!")
        }
        return true
    }

    private fun isUserInputValid(userInput: String, categoryId: Int?, categoryCount: Int): Boolean =
            userInput.isNotBlank() &&
                    (supportedExitCommands.contains(userInput) ||
                            (categoryId != null && categoryId >= 0 && categoryId < categoryCount))

    private fun processUserInput(userInput: String, transaction: Transaction, categoryId: Int?,
                                 stateContext: StateContext): Boolean {
        // numeriek
        if (categoryId != null) {
            // haal categorie op o.b.v. nummer
            val chosenCategory = stateContext.categories[categoryId]

            // sla op als trainingsdata
            stateContext.classificationService.saveAndLearn(chosenCategory, transaction)
        } else if (supportedExitCommands.contains(userInput)) {
            return false
        }

        return true
    }
}
