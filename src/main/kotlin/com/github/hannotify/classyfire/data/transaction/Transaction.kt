package com.github.hannotify.classyfire.data.transaction

import com.github.hannotify.classyfire.output.CsvOutput
import java.math.BigDecimal
import java.time.LocalDate

data class Transaction(val date: LocalDate, val description: String, val beneficiaryIban: String,
                       val amount: BigDecimal, val transactionType: String, val remarks: String) : CsvOutput {
    override fun getCsvFields(): List<String> {
        return listOf(date.toString(), description, beneficiaryIban, amount.toString(), transactionType, remarks);
    }

    fun toStringCollection() : Collection<String> {
        return listOf("date = $date",
                "description = $description",
                "beneficiaryIban = $beneficiaryIban",
                "amount = $amount",
                "transactionType = $transactionType",
                "remarks = $remarks");
    }
}
