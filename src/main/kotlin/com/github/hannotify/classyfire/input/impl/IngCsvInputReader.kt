package com.github.hannotify.classyfire.input.impl

import com.github.hannotify.classyfire.data.transaction.Transaction
import com.github.hannotify.classyfire.input.InputReader
import java.io.File
import java.math.BigDecimal
import java.nio.file.Files
import java.nio.file.Path
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class IngCsvInputReader : InputReader {
    override fun read(inputFilePath: Path): Collection<Transaction> {
        val transactions = mutableListOf<Transaction>()

        Files.lines(inputFilePath)
                .filter { !isHeaderLine(it) }
                .forEach { transactions.add(toTransaction(it)) }

        return transactions
    }

    private fun isHeaderLine(line: String): Boolean {
        return line.startsWith("\"Datum")
    }

    private fun toTransaction(line: String): Transaction {
        val fields = line.split("\",\"")
                .map { it.removePrefix("\"") }
                .map { it.removeSuffix("\"") }

        val date = LocalDate.parse(fields[0], DateTimeFormatter.ofPattern("yyyyMMdd"))
        val description = fields[1]
        val beneficiaryIban = fields[3]
        val amount = toAmount(fields[6], fields[5])
        val transactionType = fields[7]
        val remarks = fields[8]

        return Transaction(date, description, beneficiaryIban, amount, transactionType, remarks);
    }

    private fun toAmount(amountString: String, bijAf: String): BigDecimal {
        val amount = BigDecimal(amountString.replace(',', '.'))

        return if ("Bij" == bijAf) amount else amount.negate()
    }
}
