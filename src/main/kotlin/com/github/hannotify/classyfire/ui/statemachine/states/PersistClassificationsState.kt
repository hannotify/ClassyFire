package com.github.hannotify.classyfire.ui.statemachine.states

import com.github.hannotify.classyfire.ui.statemachine.State
import com.github.hannotify.classyfire.ui.statemachine.StateContext

class PersistClassificationsState : State {
    override fun next(stateContext: StateContext): State? {
        stateContext.classificationService.persist()
        println("Saved ${stateContext.classificationService.classificationCount()} classifications to file ${stateContext.classificationService.storageLocation()}.")
        return null
    }

}
