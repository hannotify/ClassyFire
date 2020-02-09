package com.github.hannotify.classyfire.data.classification.persister

import com.github.hannotify.classyfire.data.classification.ClassificationFormat

class ClassificationPersisterFactory {
    companion object {
        fun newClassificationPersister(classificationFormat: ClassificationFormat): ClassificationPersister {
            // TODO: return different implementation based on TransactionFormat enum.
            return IngCsvClassificationPersister()
        }
    }
}