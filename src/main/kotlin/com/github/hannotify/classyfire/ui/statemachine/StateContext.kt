package com.github.hannotify.classyfire.ui.statemachine

import com.github.hannotify.classyfire.data.category.CategoryRepository
import com.github.hannotify.classyfire.data.classification.ClassificationRepository
import com.github.hannotify.classyfire.data.transaction.TransactionRepository
import com.github.hannotify.classyfire.ui.Ui
import com.github.hannotify.classyfire.ui.statemachine.states.RetrieveCategoriesState

class StateContext(val categoryRepository: CategoryRepository, val transactionRepository: TransactionRepository,
                   val classificationRepository: ClassificationRepository,
                   val ui: Ui) {
    var state: State? = RetrieveCategoriesState()

    fun nextState(): State? {
        state = state?.next(this)
        return state
    }
}