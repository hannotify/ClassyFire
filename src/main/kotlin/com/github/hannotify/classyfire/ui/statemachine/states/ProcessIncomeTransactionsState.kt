package com.github.hannotify.classyfire.ui.statemachine.states

import com.github.hannotify.classyfire.data.category.CategoryType
import com.github.hannotify.classyfire.ui.statemachine.State
import com.github.hannotify.classyfire.ui.statemachine.StateContext

class ProcessIncomeTransactionsState : ProcessTransactionsState() {
    override fun next(stateContext: StateContext): State? {
        if (!processTransactions(CategoryType.INCOME, stateContext)) {
            // exit command received, proceed to persist and exit.
            return PersistClassificationsState()
        }

        // regular flow
        return ProcessExpensesTransactionsState()
    }
}
