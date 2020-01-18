package com.github.hannotify.classyfire.data.category

import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.util.*

class CategoryRepository(val storageLocation: Path? = Path.of("src/main/resources/categories.txt")) {
    private val categories: SortedSet<Category> = TreeSet()

    fun save(category: Category) {
        categories.add(category)
    }

    fun findAll(): Collection<Category> {
        return categories
    }

    fun retrieve() {
        Files.lines(storageLocation)
                .forEach { save(Category.fromString(it)) }
    }

    fun persist() {
        File(storageLocation.toString()).printWriter().use { out ->
            categories.forEach { out.println(it.toString()) }
        }
    }
}