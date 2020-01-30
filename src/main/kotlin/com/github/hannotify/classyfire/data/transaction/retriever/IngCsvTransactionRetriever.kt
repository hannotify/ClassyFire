package com.github.hannotify.classyfire.data.transaction.retriever

import com.github.hannotify.classyfire.data.transaction.Transaction
import java.math.BigDecimal
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class IngCsvTransactionRetriever : TransactionRetriever {
    override fun retrieve(transactionAsString: String): Transaction {
        val fields = transactionAsString.split("\",\"")
                .map { it.removePrefix("\"") }
                .map { it.removeSuffix("\"") }

        val date = LocalDate.parse(fields[0], DateTimeFormatter.ofPattern("yyyyMMdd"))
        val description = fields[1]
        val beneficiaryIban = fields[3]
        val amount = toAmount(fields[6], fields[5])
        val transactionType = fields[7]
        val remarks = fields[8]

        return Transaction(date, description, beneficiaryIban, amount, transactionType, remarks)
    }

    override fun isHeaderLine(line: String): Boolean {
        return line.startsWith("\"Datum")
    }

    private fun toAmount(amountString: String, bijAf: String): BigDecimal {
        val amount = BigDecimal(amountString.replace(',', '.'))

        return if ("Bij" == bijAf) amount else amount.negate()
    }
}
