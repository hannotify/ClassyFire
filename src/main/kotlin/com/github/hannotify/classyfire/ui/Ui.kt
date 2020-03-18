package com.github.hannotify.classyfire.ui

import com.github.hannotify.classyfire.data.category.CategoryRepository
import com.github.hannotify.classyfire.data.transaction.TransactionRepository
import com.github.hannotify.classyfire.process.ClassificationService
import com.github.hannotify.classyfire.ui.statemachine.State
import com.github.hannotify.classyfire.ui.statemachine.StateContext
import java.nio.file.Path

class Ui(transactionsFilePath: String) {
    private val stateContext: StateContext = StateContext(
                    CategoryRepository(Path.of("src/test/resources/categories/categories.txt")),
                    TransactionRepository(Path.of("src/test/resources/transactions/test.csv")),
                    ClassificationService(Path.of("src/test/resources/classifications/test-output.csv")))

    fun start() {
        var state: State?

        do {
            state = stateContext.nextState()
        } while (state != null)
    }
}