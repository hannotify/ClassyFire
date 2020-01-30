package com.github.hannotify.classyfire.data.transaction.retriever

import com.github.hannotify.classyfire.data.transaction.Transaction

interface TransactionRetriever {
    fun retrieve(transactionAsString: String): Transaction
    fun isHeaderLine(line: String): Boolean
}
