package com.github.hannotify.classyfire.data.transaction

import java.math.BigDecimal
import java.time.LocalDate

data class Transaction(val date: LocalDate, val description: String, val beneficiaryIban: String,
                       val amount: BigDecimal, val transactionType: String, val remarks: String) {
    fun toStringCollection() : Collection<String> {
        return listOf("date = $date",
                "description = $description",
                "beneficiaryIban = $beneficiaryIban",
                "amount = $amount",
                "transactionType = $transactionType",
                "remarks = $remarks");
    }
}
