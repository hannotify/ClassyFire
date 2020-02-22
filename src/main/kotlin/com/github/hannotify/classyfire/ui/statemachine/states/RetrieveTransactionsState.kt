package com.github.hannotify.classyfire.ui.statemachine.states

import com.github.hannotify.classyfire.ui.statemachine.State
import com.github.hannotify.classyfire.ui.statemachine.StateContext

class RetrieveTransactionsState : State {
    override fun next(stateContext: StateContext): State? {
        stateContext.transactionRepository.retrieve()
        return ProcessIncomeTransactionsState()
    }

}
