package com.github.hannotify.classyfire.data.category

import com.github.hannotify.classyfire.testdata.Categories
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.nio.file.Path

public class CategoryRepositoryTest {
    private val categoryRepository = CategoryRepository(Path.of("src/test/resources/categories/categories.txt"))

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

        val secondCategoryRepository = CategoryRepository(Path.of("src/test/resources/categories/categories.txt"))
        secondCategoryRepository.retrieve()

        // Assert
        assertEquals(categoryRepository.findAll(), secondCategoryRepository.findAll())
    }
}