package com.github.hannotify.classyfire.classification

import com.github.hannotify.classyfire.testdata.Categories.Companion.salarySubcategory
import com.github.hannotify.classyfire.testdata.Categories.Companion.waterSubcategory
import com.github.hannotify.classyfire.testdata.Transactions.Companion.salaryTransaction
import com.github.hannotify.classyfire.testdata.Transactions.Companion.waterTransaction
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

internal class ClassifierProxyTest {
    private val classifier: ClassifierProxy = ClassifierProxy()

    @BeforeTest
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
        assertEquals(salarySubcategory, classification.category)
    }

    @Test
    fun classifyWaterTransaction() {
        // Act
        val classification = classifier.classify(waterTransaction)
        // Assert
        assertEquals(waterSubcategory, classification.category)
    }
}