package com.github.hannotify.classyfire.data.category

interface CategoryRepository {
    fun add(category: Category)
    fun findAll(): Collection<Category>
}