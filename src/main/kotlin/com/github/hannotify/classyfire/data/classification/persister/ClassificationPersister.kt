package com.github.hannotify.classyfire.data.classification.persister

import com.github.hannotify.classyfire.data.classification.Classification

interface ClassificationPersister {
    fun persist(classification: Classification): String
    fun getCategoryString(classificationString: String): String
}