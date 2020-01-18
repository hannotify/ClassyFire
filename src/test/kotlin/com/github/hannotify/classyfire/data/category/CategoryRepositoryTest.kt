package com.github.hannotify.classyfire.data.category

import com.github.hannotify.classyfire.testdata.Categories
import java.nio.file.Path
import kotlin.test.Test
import kotlin.test.assertEquals

internal class CategoryRepositoryTest {
    private val categoryRepository = CategoryRepository(Path.of("src/test/resources/categories.txt"))

    @Test
    internal fun save_addsCategory() {
        // Arrange
        // Act
        categoryRepository.save(Categories.houseCategory)
        categoryRepository.save(Categories.myIncomeCategory)

        // Assert
        assertEquals(2, categoryRepository.findAll().size)
    }

    @Test
    internal fun persistAndRetrieve_returnsTheSameCategories() {
        // Arrange
        categoryRepository.save(Categories.houseCategory)
        categoryRepository.save(Categories.myIncomeCategory)
        categoryRepository.save(Categories.waterSubcategory)
        categoryRepository.save(Categories.mortgageSubcategory)
        categoryRepository.save(Categories.powerSubcategory)
        categoryRepository.save(Categories.salarySubcategory)

        // Act
        categoryRepository.persist()

        val secondCategoryRepository = CategoryRepository(Path.of("src/test/resources/categories.txt"))
        secondCategoryRepository.retrieve()

        // Assert
        assertEquals(categoryRepository.findAll(), secondCategoryRepository.findAll())
    }
}