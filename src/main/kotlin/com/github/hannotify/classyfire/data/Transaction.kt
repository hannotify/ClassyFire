package com.github.hannotify.classyfire.data

import java.math.BigDecimal
import java.time.LocalDate

data class Transaction(val date: LocalDate, val description: String, val beneficiaryIban: String,
                       val amount: BigDecimal, val transactionType: String, val remarks: String)
