package com.github.hannotify.classyfire.data.transaction

import java.nio.file.Path
import kotlin.test.Test
import kotlin.test.assertEquals

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