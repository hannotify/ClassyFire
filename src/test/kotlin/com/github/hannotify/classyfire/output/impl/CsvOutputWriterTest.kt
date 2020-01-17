package com.github.hannotify.classyfire.output.impl

import com.github.hannotify.classyfire.data.classification.Classification
import com.github.hannotify.classyfire.output.OutputWriter
import com.github.hannotify.classyfire.testdata.Categories
import com.github.hannotify.classyfire.testdata.Transactions
import java.nio.file.Files
import java.nio.file.Path
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class CsvOutputWriterTest {
    private val outputWriter: OutputWriter = CsvOutputWriter()
    private val classifications: Collection<Classification> = listOf(
            Classification(Transactions.waterTransaction, Categories.waterSubcategory, 0.75f),
            Classification(Transactions.salaryTransaction, Categories.salarySubcategory, 0.95f)
    )

    @Test
    fun write() {
        // Arrange
        val outputFilePath = Path.of("src/test/resources/test-output.csv")
        val expectedCsvContent = """
            2019-01-21;Monthly water bill;NL01RABO0001234567;12.0;Wire Transfer;87HTNSH;Water / House (EXPENSES)
            2019-01-18;Your salary for January 2019;NL01INGB0001234567;1000.0;Wire Transfer;3BDACFB2488C9F2E98165E99C5411AF5;Salary / My income (INCOME)
            
        """.trimIndent()

        // Act
        outputWriter.write(classifications, outputFilePath)

        // Assert
        assertTrue(Files.exists(outputFilePath))
        assertEquals(expectedCsvContent, Files.readString(outputFilePath))
    }
}