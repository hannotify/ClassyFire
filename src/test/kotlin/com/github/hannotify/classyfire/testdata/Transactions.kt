package com.github.hannotify.classyfire.testdata

import com.github.hannotify.classyfire.data.transaction.Transaction
import java.math.BigDecimal
import java.time.LocalDate

class Transactions {
    companion object {
        @JvmStatic val waterTransaction = Transaction(
                date = LocalDate.of(2019, 1, 21),
                description = "Monthly water bill",
                beneficiaryIban = "NL01RABO0001234567",
                amount = BigDecimal.valueOf(12.0),
                transactionType = "Wire Transfer",
                remarks = "87HTNSH")
        @JvmStatic val salaryTransaction = Transaction(
                date = LocalDate.of(2019, 1, 18),
                description = "Your salary for January 2019",
                beneficiaryIban = "NL01INGB0001234567",
                amount = BigDecimal.valueOf(1000.0),
                transactionType = "Wire Transfer",
                remarks = "3BDACFB2488C9F2E98165E99C5411AF5"
        )
    }
}