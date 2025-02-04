package com.github.hannotify.classyfire.ui.statemachine

import com.github.hannotify.classyfire.data.category.Category
import com.github.hannotify.classyfire.data.category.CategoryRepository
import com.github.hannotify.classyfire.data.category.CategoryType
import com.github.hannotify.classyfire.data.classification.Classification
import com.github.hannotify.classyfire.data.transaction.Transaction
import com.github.hannotify.classyfire.data.transaction.TransactionRepository
import com.github.hannotify.classyfire.process.ClassificationService
import com.github.hannotify.classyfire.ui.statemachine.states.RetrieveCategoriesState
import kotlin.math.ceil
import kotlin.math.max

class StateContext(val categoryRepository: CategoryRepository, val transactionRepository: TransactionRepository,
                   val classificationService: ClassificationService) {
    var state: State? = RetrieveCategoriesState()
    lateinit var categories: List<Category>
    lateinit var processedTransactions: List<Transaction>

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

    private fun printCategories(categories: List<Category?>) {
        val splitSize = max(categories.size / 2, 1)
        val padding = categories.size.toString().length
        val categoryMap = categories.withIndex()
                .groupBy { it.index % splitSize }
                .map { c -> c.value.map { it.value } }
                .toMutableList()

        if (categories.size % 2 != 0) {
            // TODO: do something to expand the second column
            categoryMap.add(listOf(null, categories[categories.size - 1]))
        }


        // 2 columns:
        categoryMap.forEachIndexed { index, pairList ->
            println("%-60.60s %-60.60s".format(
                    pairList[0]?.let { "${index.toString().padStart(padding)} - ${pairList[0]}" },
                    if (pairList.size > 1) "${(index + splitSize).toString().padStart(padding)} - ${pairList[1]}" else ""
            ))
        }

        println()
    }

    internal fun classify(transaction: Transaction): Classification? = classificationService.classify(transaction)

    fun processTrainingData(categoryType: CategoryType) {
        processedTransactions = classificationService.processTrainingData(categoryType)
    }
}
