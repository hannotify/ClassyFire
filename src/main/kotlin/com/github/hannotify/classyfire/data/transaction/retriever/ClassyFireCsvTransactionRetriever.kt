package com.github.hannotify.classyfire.data.transaction.retriever

import com.github.hannotify.classyfire.data.transaction.Transaction
import java.math.BigDecimal
import java.text.NumberFormat
import java.time.LocalDate

class ClassyFireCsvTransactionRetriever : TransactionRetriever {
    override fun retrieve(transactionAsString: String): Transaction {
        val fields = transactionAsString.split("\t")
        val date = LocalDate.parse(fields[0])
        val description = fields[1]
        val beneficiaryIban = fields[2]
        val amount = BigDecimal(fields[3].replace(',', '.'))
        val transactionType = fields[4]
        val remarks = fields[5]
        // index 6 = Category, we skip it here.

        return Transaction(date, description, beneficiaryIban, amount, transactionType, remarks)
    }

    override fun isHeaderLine(line: String): Boolean {
        return false
    }
}
