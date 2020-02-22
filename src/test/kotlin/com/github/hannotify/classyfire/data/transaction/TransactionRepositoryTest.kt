package com.github.hannotify.classyfire.data.transaction

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
        assertEquals(96, transactions.size);
        assertEquals(LocalDate.of(2019, 1, 1), transactions.elementAt(0).date)
        assertEquals(LocalDate.of(2019, 1, 31), transactions.elementAt(transactions.size - 1).date)
        transactions.forEach { println(it) }
    }

    @Test
    fun findIncomeTransactions_shouldFindIncomeTransactionsOnly() {
        // Act
        val incomeTransactions = transactionRepository.findTransactionsByCategoryType()

        // Assert
        assertEquals(17, incomeTransactions.size)
        assertEquals(LocalDate.of(2019, 1, 1), incomeTransactions.elementAt(0).date)
        assertEquals(LocalDate.of(2019, 1, 26), incomeTransactions.elementAt(incomeTransactions.size - 1).date)
        incomeTransactions.forEach { println(it) }
    }

    @Test
    fun findExpenseTransactions_shouldFindExpenseTransactionsOnly() {
        // Act
        val expenseTransactions = transactionRepository.findExpenseTransactions()

        // Assert
        assertEquals(79, expenseTransactions.size)
        assertEquals(LocalDate.of(2019, 1, 1), expenseTransactions.elementAt(0).date)
        assertEquals(LocalDate.of(2019, 1, 31), expenseTransactions.elementAt(expenseTransactions.size - 1).date)
    }


}