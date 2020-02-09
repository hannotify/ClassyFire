package com.github.hannotify.classyfire.data.transaction

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.nio.file.Path

internal class TransactionRepositoryTest {
    // Arrange
    private val transactionRepository = TransactionRepository(Path.of("src/test/resources/test.csv"))

    @Test
    fun retrieve_loads90Transactions() {
        // Act
        transactionRepository.retrieve()

        // Assert
        assertEquals(96, transactionRepository.findAll().size);
    }
}