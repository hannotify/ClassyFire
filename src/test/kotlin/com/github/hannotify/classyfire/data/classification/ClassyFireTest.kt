package com.github.hannotify.classyfire.data.classification

import com.github.hannotify.classyfire.data.category.Category
import com.github.hannotify.classyfire.data.category.CategoryType.EXPENSES
import com.github.hannotify.classyfire.data.category.CategoryType.INCOME
import com.github.hannotify.classyfire.data.transaction.Transaction
import java.math.BigDecimal
import java.time.LocalDate
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

internal class ClassyFireTest {
    private val classifier: ClassyFire = ClassyFire()

    //region Income categories
    private val myIncomeCategory: Category = Category(INCOME, "My income")
    private val salarySubcategory: Category = Category(INCOME, "Salary", myIncomeCategory)
    //endregion

    //regionExpenses categories
    private val houseCategory: Category = Category(EXPENSES, "House")
    private val mortgageSubcategory: Category = Category(EXPENSES, "Mortgage", houseCategory)
    private val powerSubcategory: Category = Category(EXPENSES, "Power", houseCategory)
    private val waterSubcategory: Category = Category(EXPENSES, "Water", houseCategory)
    //endregion

    //region Transactions
    private val salaryTransaction = Transaction(
            date = LocalDate.of(2019, 1, 18),
            description = "Your salary for January 2019",
            beneficiaryIban = "NL01INGB0001234567",
            amount = BigDecimal.valueOf(1000.0),
            transactionType = "Wire Transfer",
            remarks = "3BDACFB2488C9F2E98165E99C5411AF5"
    )
    private val waterTransaction = Transaction(
            date = LocalDate.of(2019, 1, 21),
            description = "Monthly water bill",
            beneficiaryIban = "NL01RABO0001234567",
            amount = BigDecimal.valueOf(12.0),
            transactionType = "Wire Transfer",
            remarks = "87HTNSH"
    )
    //endregion

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