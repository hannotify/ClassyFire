package com.github.hannotify.classyfire.data.transaction

import com.github.hannotify.classyfire.testdata.Transactions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.assertj.core.api.Assertions.*

internal class TransactionTest {

    @Test
    fun compareTo_shouldOrderByDate() {
        // Arrange
        // Act
        // Assert
        assertThat(Transactions.waterTransaction.compareTo(Transactions.salaryTransaction))
                .isGreaterThan(0)
        assertThat(Transactions.salaryTransaction.compareTo(Transactions.waterTransaction))
                .isLessThan(0)
    }
}