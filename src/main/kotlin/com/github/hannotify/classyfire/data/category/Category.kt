package com.github.hannotify.classyfire.data.category

import com.github.hannotify.classyfire.output.CsvOutput

data class Category (val categoryType: CategoryType, val name: String, val superCategory: Category? = null): CsvOutput, Comparable<Category> {

    override fun getCsvFields(): List<String> {
        return listOf(toString());
    }

    override fun toString(): String {
        return name + if (superCategory != null) "$SUPER_CATEGORY_DELIMITER$superCategory" else " ($categoryType)"
    }

    companion object {
        val SUPER_CATEGORY_DELIMITER = " / "

        fun fromString(categoryString: String, categoryType: CategoryType? = null): Category {
            var _categoryType = categoryType
            var categoryStrings = categoryString.split(" (")

            if (categoryStrings.size > 1 && categoryType == null) {
                _categoryType = CategoryType.valueOf(categoryStrings[1].dropLast(1))
            }

            categoryStrings = categoryStrings[0].split(delimiters = *arrayOf(SUPER_CATEGORY_DELIMITER), limit = 2)

            val hasSuperCategory = categoryStrings.size > 1
            return Category(_categoryType!!, categoryStrings[0], if (hasSuperCategory) fromString(categoryStrings[1], _categoryType) else null)
        }
    }

    override fun compareTo(other: Category): Int {
        return name.compareTo(other.name)
    }
}
