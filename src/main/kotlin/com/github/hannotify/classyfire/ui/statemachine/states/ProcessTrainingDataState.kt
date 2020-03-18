package com.github.hannotify.classyfire.ui.statemachine.states

import com.github.hannotify.classyfire.ui.statemachine.State
import com.github.hannotify.classyfire.ui.statemachine.StateContext

class ProcessTrainingDataState : State {
    override fun next(stateContext: StateContext): State? {
        stateContext.processTrainingData()
        return RetrieveCategoriesState()
    }
}