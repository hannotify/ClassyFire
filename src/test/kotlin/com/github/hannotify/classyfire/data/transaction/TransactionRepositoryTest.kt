package com.github.hannotify.classyfire.data.transaction

import com.github.hannotify.classyfire.data.category.CategoryType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.nio.file.Path
import java.time.LocalDate

internal class TransactionRepositoryTest {
    // Arrange
    private val transactionRepository = TransactionRepository(Path.of("src/test/resources/test.csv"))

    @BeforeEach
    fun setup() {
        transactionRepository.retrieve()
    }

    @Test
    fun retrieveAndFindAll_shouldLoad96Transactions_sortedByDateAscending() {
        // Act
        val transactions = transactionRepository.findAll()

        // Assert
        assertEquals(40, transactions.size);
        assertEquals(LocalDate.of(2021, 10, 1), transactions.elementAt(0).date)
        assertEquals(LocalDate.of(2021, 11, 9), transactions.elementAt(transactions.size - 1).date)
        transactions.forEach { println(it) }
    }

    @Test
    fun findIncomeTransactions_shouldFindIncomeTransactionsOnly() {
        // Act
        val incomeTransactions = transactionRepository.findTransactionsByCategoryType(CategoryType.INCOME)

        // Assert
        assertEquals(3, incomeTransactions.size)
        assertEquals(LocalDate.of(2021, 10, 4), incomeTransactions.elementAt(0).date)
        assertEquals(LocalDate.of(2021, 10, 31), incomeTransactions.elementAt(incomeTransactions.size - 1).date)
        incomeTransactions.forEach { println(it) }
    }

    @Test
    fun findExpenseTransactions_shouldFindExpenseTransactionsOnly() {
        // Act
        val expenseTransactions = transactionRepository.findTransactionsByCategoryType(CategoryType.EXPENSES)

        // Assert
        assertEquals(37, expenseTransactions.size)
        assertEquals(LocalDate.of(2021, 10, 1), expenseTransactions.elementAt(0).date)
        assertEquals(LocalDate.of(2021, 11, 9), expenseTransactions.elementAt(expenseTransactions.size - 1).date)
    }


}