package com.github.hannotify.classyfire.ui.statemachine.states

import com.github.hannotify.classyfire.data.category.CategoryType
import com.github.hannotify.classyfire.ui.statemachine.State
import com.github.hannotify.classyfire.ui.statemachine.StateContext

class ProcessExpensesTransactionsState : ProcessTransactionsState() {
    override fun next(stateContext: StateContext): State? {
        processTransactions(CategoryType.EXPENSES, stateContext)
        return PersistClassificationsState()
    }
}
