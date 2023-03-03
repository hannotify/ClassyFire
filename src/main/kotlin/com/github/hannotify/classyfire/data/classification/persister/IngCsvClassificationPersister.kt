package com.github.hannotify.classyfire.data.classification.persister

import com.github.hannotify.classyfire.data.classification.Classification
import java.text.NumberFormat
import java.util.stream.Collectors

class IngCsvClassificationPersister : ClassificationPersister {
    private val DELIMITER = "\t"

    override fun persist(classification: Classification): String {
        return listOf(classification.transaction.date.toString(),
                classification.transaction.description,
                classification.transaction.beneficiaryIban,
                classification.transaction.amount.toPlainString().replace('.', ','),
                classification.transaction.transactionType,
                classification.transaction.remarks,
                classification.category.toString()).joinToString(DELIMITER)
    }

    override fun getCategoryString(classificationString: String): String {
        return classificationString.substringAfterLast(DELIMITER)
    }
}
