package com.github.hannotify.classyfire.data.transaction.retriever

import com.github.hannotify.classyfire.data.transaction.TransactionFormat
import com.github.hannotify.classyfire.data.transaction.TransactionFormat.CLASSY_FIRE_CSV
import com.github.hannotify.classyfire.data.transaction.TransactionFormat.ING_CSV

class TransactionRetrieverFactory {
    companion object {
        fun newTransactionRetriever(transactionFormat: TransactionFormat): TransactionRetriever {
            return when (transactionFormat) {
                CLASSY_FIRE_CSV -> ClassyFireCsvTransactionRetriever()
                ING_CSV -> IngCsvTransactionRetriever()
            }
        }
    }
}