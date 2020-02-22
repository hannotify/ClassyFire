package com.github.hannotify.classyfire.ui.statemachine.states

import com.github.hannotify.classyfire.data.category.CategoryType
import com.github.hannotify.classyfire.ui.statemachine.State
import com.github.hannotify.classyfire.ui.statemachine.StateContext

abstract class ProcessTransactionsState : State {
    protected fun processTransactions(categoryType: CategoryType, stateContext: StateContext) {
        stateContext.ui.clearScreen()

        val transactions = stateContext.transactionRepository.findTransactionsByCategoryType(categoryType)
        transactions.forEachIndexed { index, transaction ->
            stateContext.ui.printCategoriesByCategoryType(categoryType)
            transaction.print(index, transactions.size)
            print("Enter category: ")
            val userInput = readLine()
            stateContext.ui.clearScreen()
            println("User entered $userInput!")
            println()
        }
    }
}