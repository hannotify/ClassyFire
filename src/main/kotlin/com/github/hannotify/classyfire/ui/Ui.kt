package com.github.hannotify.classyfire.ui

import com.github.hannotify.classyfire.data.category.CategoryRepository
import com.github.hannotify.classyfire.data.transaction.TransactionRepository
import com.github.hannotify.classyfire.process.ClassificationService
import com.github.hannotify.classyfire.ui.statemachine.State
import com.github.hannotify.classyfire.ui.statemachine.StateContext
import java.nio.file.Path

class Ui(categoriesFilePath: String, transactionsFilePath: String, classificationFilePath: String) {
    private val stateContext: StateContext = StateContext(
                    CategoryRepository(Path.of(categoriesFilePath)),
                    TransactionRepository(Path.of(transactionsFilePath)),
                    ClassificationService(Path.of(classificationFilePath)))

    fun start() {
        var state: State?

        do {
            state = stateContext.nextState()
        } while (state != null)
    }
}