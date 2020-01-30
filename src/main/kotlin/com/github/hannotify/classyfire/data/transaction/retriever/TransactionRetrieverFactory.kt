package com.github.hannotify.classyfire.data.transaction.retriever

import com.github.hannotify.classyfire.data.transaction.TransactionFormat

class TransactionRetrieverFactory {
    companion object {
        fun newTransactionRetriever(transactionFormat: TransactionFormat): TransactionRetriever {
            // TODO: return different implementation based on TransactionFormat enum.
            return IngCsvTransactionRetriever()
        }
    }
}