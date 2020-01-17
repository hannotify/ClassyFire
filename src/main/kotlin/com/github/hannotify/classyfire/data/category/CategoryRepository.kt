package com.github.hannotify.classyfire.data.category

import java.nio.file.Path

abstract class CategoryRepository {
    private val STORAGE_LOCATION = Path.of("src/main/resources/categories.txt")

    abstract fun add(category: Category)
    abstract fun exists(category: Category): Boolean
    abstract fun findAll(): Collection<Category>
    abstract fun retrieve()
    abstract fun persist()
}