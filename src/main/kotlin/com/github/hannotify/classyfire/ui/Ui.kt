package com.github.hannotify.classyfire.ui

import com.github.hannotify.classyfire.data.category.Category
import com.github.hannotify.classyfire.data.category.CategoryRepository
import com.github.hannotify.classyfire.data.category.CategoryType
import java.nio.file.Path
import java.util.stream.Collectors

class Ui {
    fun print() {
        clearScreen()
        printCategories()
        //printInput()
    }

    private fun clearScreen() {
        print("\u001b[H\u001b[2J");
        System.out.flush();
    }

    private fun printCategories() {
        val categoryRepository = CategoryRepository(Path.of("src/test/resources/categories.txt"))
        categoryRepository.retrieve();
        val categories = categoryRepository.findAll();

        val subcategoriesByType = categories.stream()
                .filter(Category::isSubcategory)
                .collect(Collectors.groupingBy(Category::categoryType))

        println("Income Categories:")

        val incomeSubcategories = subcategoriesByType[CategoryType.INCOME]
        val paddingForIncomeSubcategories = incomeSubcategories?.size.toString().length
        incomeSubcategories?.forEachIndexed { index, element ->
            println("${index.toString().padStart(paddingForIncomeSubcategories)} - $element")
        }
        println()
        println("Expense Categories:")
        val expenseSubcategories = subcategoriesByType[CategoryType.EXPENSES]
        val paddingForExpenseSubcategories = expenseSubcategories?.size.toString().length
        expenseSubcategories?.forEachIndexed { index, element ->
            println("${(index + incomeSubcategories?.size!!).toString().padStart(paddingForExpenseSubcategories)} - $element")
        }
    }
}