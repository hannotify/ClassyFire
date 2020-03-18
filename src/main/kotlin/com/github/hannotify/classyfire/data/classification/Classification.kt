package com.github.hannotify.classyfire.data.classification

import com.github.hannotify.classyfire.data.category.Category
import com.github.hannotify.classyfire.data.classification.persister.ClassificationPersisterFactory
import com.github.hannotify.classyfire.data.transaction.Transaction
import com.github.hannotify.classyfire.data.transaction.TransactionFormat
import com.github.hannotify.classyfire.data.transaction.retriever.TransactionRetrieverFactory

class Classification (val transaction: Transaction, val category: Category, val probability: Float = 0.0f)
        : Comparable<Classification> {
    override fun compareTo(other: Classification): Int = transaction.compareTo(other.transaction)

    companion object {
        fun fromString(classificationAsString: String): Classification {
            val transaction = TransactionRetrieverFactory.newTransactionRetriever(TransactionFormat.CLASSY_FIRE_CSV)
                    .retrieve(classificationAsString.substringBeforeLast(";"))

            val category = Category.fromString(ClassificationPersisterFactory.newClassificationPersister(
                    ClassificationFormat.ING_CSV).getCategoryString(classificationAsString))

            return Classification(transaction, category)
        }
    }
}
