package com.github.hannotify.classyfire.ui

import com.github.hannotify.classyfire.data.category.Category
import com.github.hannotify.classyfire.data.category.CategoryRepository
import com.github.hannotify.classyfire.data.category.CategoryType
import com.github.hannotify.classyfire.data.classification.ClassificationRepository
import com.github.hannotify.classyfire.data.transaction.Transaction
import com.github.hannotify.classyfire.data.transaction.TransactionRepository
import java.nio.file.Path

class Ui(transactionsFilePath: String) {
    private val categoryRepository: CategoryRepository =
            CategoryRepository(Path.of("src/test/resources/categories.txt"))
    private val transactionRepository: TransactionRepository =
            TransactionRepository(Path.of("src/test/resources/test.csv"))
    private val classificationRepository: ClassificationRepository =
            ClassificationRepository(Path.of("src/test/resources/test-output.csv"))

    init {
        categoryRepository.retrieve()
        transactionRepository.retrieve()
    }

    fun start() {
        clearScreen()
        processTransactions()
        persistClassifications()
    }

    private fun clearScreen() {
        print("\u001b[H\u001b[2J");
        System.out.flush();
    }

    private fun processTransactions() {
        processTransactions(CategoryType.INCOME)
        processTransactions(CategoryType.EXPENSES)
    }

    private fun processTransactions(categoryType: CategoryType) {
        val transactions = transactionRepository.findTransactionsByCategoryType(categoryType)
        transactions.forEachIndexed { index, transaction ->
            printCategoriesByCategoryType(categoryType)
            transaction.print(index, transactions.size)
            print("Enter category: ")
            val userInput = readLine()
            clearScreen()
            println("User entered $userInput!")
            println()
        }
    }

    private fun persistClassifications() {
        classificationRepository.persist()
    }

    private fun printCategoriesByCategoryType(categoryType: CategoryType) {
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