package com.github.hannotify.classyfire.ui.statemachine.states

import com.github.hannotify.classyfire.data.category.CategoryType
import com.github.hannotify.classyfire.ui.statemachine.State
import com.github.hannotify.classyfire.ui.statemachine.StateContext

class ProcessTrainingDataState(private val categoryType: CategoryType) : State {
    override fun next(stateContext: StateContext): State? {
        stateContext.processTrainingData(categoryType)

        return if (categoryType == CategoryType.INCOME)
            ProcessIncomeTransactionsState()
        else
            ProcessExpensesTransactionsState()
    }
}