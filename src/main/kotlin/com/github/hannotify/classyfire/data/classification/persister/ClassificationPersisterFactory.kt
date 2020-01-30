package com.github.hannotify.classyfire.data.classification.persister

import com.github.hannotify.classyfire.data.classification.ClassificationFormat
import com.github.hannotify.classyfire.data.transaction.TransactionFormat
import com.github.hannotify.classyfire.data.transaction.retriever.IngCsvTransactionRetriever
import com.github.hannotify.classyfire.data.transaction.retriever.TransactionRetriever

class ClassificationPersisterFactory {
    companion object {
        fun newClassificationPersister(classificationFormat: ClassificationFormat): ClassificationPersister {
            // TODO: return different implementation based on TransactionFormat enum.
            return IngCsvClassificationPersister()
        }
    }
}