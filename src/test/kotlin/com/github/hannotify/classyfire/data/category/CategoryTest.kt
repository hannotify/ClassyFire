package com.github.hannotify.classyfire.data.category

import com.github.hannotify.classyfire.testdata.Categories
import kotlin.test.Test
import kotlin.test.assertEquals

internal class CategoryTest {

    @Test
    fun toString_shouldReturnTheRightStringRepresentation() {
        // Arrange
        // Act
        // Assert
        assertEquals("Power / House (EXPENSES)", Categories.powerSubcategory.toString())
    }

    @Test
    fun fromString_shouldResultInAValidCategory() {
        // Arrange
        // Act
        // Assert
        assertEquals(Categories.powerSubcategory, Category.fromString("Power / House (EXPENSES)"))
    }
}