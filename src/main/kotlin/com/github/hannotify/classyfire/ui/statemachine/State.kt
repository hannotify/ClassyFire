package com.github.hannotify.classyfire.ui.statemachine

interface State {
    fun next(stateContext: StateContext): State?
}
