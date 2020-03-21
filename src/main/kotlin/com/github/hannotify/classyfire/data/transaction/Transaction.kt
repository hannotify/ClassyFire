package com.github.hannotify.classyfire.data.transaction

import java.math.BigDecimal
import java.time.LocalDate

data class Transaction(val date: LocalDate, val description: String, val beneficiaryIban: String,
                       val amount: BigDecimal, val transactionType: String, val remarks: String) : Comparable<Transaction> {
    fun toStringCollection() : Collection<String> {
        return listOf("date = $date",
                "description = $description",
                "beneficiaryIban = $beneficiaryIban",
                "amount = $amount",
                "transactionType = $transactionType",
                "remarks = $remarks");
    }

    override fun compareTo(other: Transaction): Int = compareValuesBy(
                this, other, { it.date }, { it.description }, { it.remarks }, { it.beneficiaryIban }, { it.amount })

    fun print(index: Int, size: Int) {
        val transactionHeader = "Transaction #${index + 1}/$size"
        println(transactionHeader)
        println("-".repeat(transactionHeader.length))
        println("Date: $date   Amount: $amount   Description: $description")
        println("Remarks: ${System.lineSeparator()}$remarks")
        println()
    }
}
