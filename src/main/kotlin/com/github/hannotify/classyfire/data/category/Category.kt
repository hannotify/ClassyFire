package com.github.hannotify.classyfire.data.category

import com.github.hannotify.classyfire.output.CsvOutput

data class Category (val categoryType: CategoryType, val name: String, val superCategory: Category? = null): CsvOutput {
    override fun getCsvFields(): List<String> {
        return listOf(toString());
    }

    override fun toString(): String {
        return name + if (superCategory != null) " / $superCategory" else " ($categoryType)"
    }
}
