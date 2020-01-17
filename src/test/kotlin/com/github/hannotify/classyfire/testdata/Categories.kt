package com.github.hannotify.classyfire.testdata

import com.github.hannotify.classyfire.data.category.Category
import com.github.hannotify.classyfire.data.category.CategoryType

class Categories {
    companion object {
        @JvmStatic val myIncomeCategory: Category = Category(CategoryType.INCOME, "My income")
        @JvmStatic val salarySubcategory: Category = Category(CategoryType.INCOME, "Salary", myIncomeCategory)
        @JvmStatic val houseCategory: Category = Category(CategoryType.EXPENSES, "House")
        @JvmStatic val mortgageSubcategory: Category = Category(CategoryType.EXPENSES, "Mortgage", houseCategory)
        @JvmStatic val powerSubcategory: Category = Category(CategoryType.EXPENSES, "Power", houseCategory)
        @JvmStatic val waterSubcategory: Category = Category(CategoryType.EXPENSES, "Water", houseCategory)
    }
}