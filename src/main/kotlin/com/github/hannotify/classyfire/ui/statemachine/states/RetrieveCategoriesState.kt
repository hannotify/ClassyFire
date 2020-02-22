package com.github.hannotify.classyfire.ui.statemachine.states

import com.github.hannotify.classyfire.ui.statemachine.State
import com.github.hannotify.classyfire.ui.statemachine.StateContext

class RetrieveCategoriesState : State {

    override fun next(stateContext: StateContext): State? {
        stateContext.categoryRepository.retrieve()
        return RetrieveTransactionsState()
    }
}
