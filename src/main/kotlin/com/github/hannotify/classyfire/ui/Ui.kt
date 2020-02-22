package com.github.hannotify.classyfire.ui

import com.github.hannotify.classyfire.data.category.Category
import com.github.hannotify.classyfire.data.category.CategoryRepository
import com.github.hannotify.classyfire.data.category.CategoryType
import com.github.hannotify.classyfire.data.classification.ClassificationRepository
import com.github.hannotify.classyfire.data.transaction.TransactionRepository
import com.github.hannotify.classyfire.ui.statemachine.State
import com.github.hannotify.classyfire.ui.statemachine.StateContext
import java.nio.file.Path

class Ui(transactionsFilePath: String) {
    private val categoryRepository: CategoryRepository =
            CategoryRepository(Path.of("src/test/resources/categories.txt"))
    private val transactionRepository: TransactionRepository =
            TransactionRepository(Path.of("src/test/resources/test.csv"))
    private val classificationRepository: ClassificationRepository =
            ClassificationRepository(Path.of("src/test/resources/test-output.csv"))
    private val stateContext: StateContext =
            StateContext(categoryRepository, transactionRepository, classificationRepository, this)

    init {
        categoryRepository.retrieve()
        transactionRepository.retrieve()
    }

    fun start() {
        var state: State?

        do {
            state = stateContext.nextState()
        } while (state != null)
    }

    internal fun clearScreen() {
        print("\u001b[H\u001b[2J")
        System.out.flush()
    }

    internal fun printCategoriesByCategoryType(categoryType: CategoryType) {
        println("Categories ($categoryType):")
        printCategories(categoryRepository.findSubcategoriesByCategoryType(categoryType))
    }

    private fun printCategories(categories: Collection<Category>) {
        val padding = categories.size.toString().length
        categories.forEachIndexed { index, element ->
            println("${index.toString().padStart(padding)} - $element")
        }
        println()
    }
}