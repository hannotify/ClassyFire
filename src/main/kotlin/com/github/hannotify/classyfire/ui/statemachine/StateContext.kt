package com.github.hannotify.classyfire.ui.statemachine

import com.github.hannotify.classyfire.data.category.Category
import com.github.hannotify.classyfire.data.category.CategoryRepository
import com.github.hannotify.classyfire.data.category.CategoryType
import com.github.hannotify.classyfire.data.classification.Classification
import com.github.hannotify.classyfire.data.classification.ClassificationRepository
import com.github.hannotify.classyfire.data.transaction.Transaction
import com.github.hannotify.classyfire.data.transaction.TransactionRepository
import com.github.hannotify.classyfire.process.ClassificationService
import com.github.hannotify.classyfire.ui.Ui
import com.github.hannotify.classyfire.ui.statemachine.states.ProcessTrainingDataState
import com.github.hannotify.classyfire.ui.statemachine.states.RetrieveCategoriesState

class StateContext(val categoryRepository: CategoryRepository, val transactionRepository: TransactionRepository,
                   val classificationService: ClassificationService) {
    var state: State? = ProcessTrainingDataState()
    lateinit var categories: List<Category>

    fun nextState(): State? {
        state = state?.next(this)
        return state
    }

    internal fun clearScreen() {
        print("\u001b[H\u001b[2J")
        System.out.flush()
    }

    internal fun printCategoriesByCategoryType(categoryType: CategoryType) {
        println("Categories ($categoryType):")
        categories = categoryRepository.findSubcategoriesByCategoryType(categoryType)
        printCategories(categories)
    }

    private fun printCategories(categories: Collection<Category>) {
        val padding = categories.size.toString().length
        categories.forEachIndexed { index, element ->
            println("${index.toString().padStart(padding)} - $element")
        }
        println()
    }

    internal fun classify(transaction: Transaction): Classification = classificationService.classify(transaction)
}