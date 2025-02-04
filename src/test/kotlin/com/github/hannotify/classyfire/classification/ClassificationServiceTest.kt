package com.github.hannotify.classyfire.classification

import com.github.hannotify.classyfire.process.ClassificationService
import com.github.hannotify.classyfire.testdata.Categories.Companion.salarySubcategory
import com.github.hannotify.classyfire.testdata.Categories.Companion.waterSubcategory
import com.github.hannotify.classyfire.testdata.Transactions.Companion.salaryTransaction
import com.github.hannotify.classyfire.testdata.Transactions.Companion.waterTransaction
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.nio.file.Path

class ClassificationServiceTest {
    private val classifier: ClassificationService = ClassificationService(Path.of(""))

    @BeforeEach
    fun learn() {
        // Arrange
        classifier.learn(salarySubcategory, salaryTransaction)
        classifier.learn(waterSubcategory, waterTransaction)
    }

    @Test
    fun classifySalaryTransaction() {
        // Act
        val classification = classifier.classify(salaryTransaction)
        // Assert
        assertEquals(salarySubcategory, classification?.category)
    }

    @Test
    fun classifyWaterTransaction() {
        // Act
        val classification = classifier.classify(waterTransaction)
        // Assert
        assertEquals(waterSubcategory, classification?.category)
    }
}