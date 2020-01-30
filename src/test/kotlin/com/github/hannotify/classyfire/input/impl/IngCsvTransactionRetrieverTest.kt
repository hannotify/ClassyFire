package com.github.hannotify.classyfire.input.impl

import com.github.hannotify.classyfire.data.transaction.retriever.IngCsvTransactionRetriever
import java.nio.file.Path
import kotlin.test.Test
import kotlin.test.assertEquals

internal class IngCsvTransactionRetrieverTest {

    @Test
    fun read() {
        // Arrange
        val inputReader = IngCsvTransactionRetriever()

        // Act
        val transactions = inputReader.read(Path.of("src/test/resources/test.csv"))

        // Assert
        assertEquals(96, transactions.size);
        println(transactions)
    }
}