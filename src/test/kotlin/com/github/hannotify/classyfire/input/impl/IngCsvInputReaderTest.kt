package com.github.hannotify.classyfire.input.impl

import kotlin.test.Test
import kotlin.test.assertEquals

internal class IngCsvInputReaderTest {

    @Test
    fun read() {
        // Arrange
        val inputReader = IngCsvInputReader()

        // Act
        val transactions = inputReader.read("src/test/resources/test.csv")

        // Assert
        assertEquals(96, transactions.size);
        println(transactions)
    }
}