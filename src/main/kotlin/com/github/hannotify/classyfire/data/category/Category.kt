package com.github.hannotify.classyfire.data.category

data class Category (val categoryType: CategoryType, val name: String, val superCategory: Category? = null) {
    override fun toString(): String {
        return name + if (superCategory != null) " / $superCategory" else " ($categoryType)"
    }
}
