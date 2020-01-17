package com.github.hannotify.classyfire.classification

import com.github.hannotify.classyfire.classification.Classification
import com.github.hannotify.classyfire.data.category.Category
import com.github.hannotify.classyfire.data.transaction.Transaction
import de.daslaboratorium.machinelearning.classifier.Classifier
import de.daslaboratorium.machinelearning.classifier.bayes.BayesClassifier

class ClassyFire {
    val classifier: Classifier<String, Category> = BayesClassifier()

    fun learn(category: Category, transaction: Transaction) {
        classifier.learn(category, transaction.toStringCollection())
    }

    fun classify(transaction: Transaction): Classification {
        val internalClassification = classifier.classify(transaction.toStringCollection())

        return Classification(transaction, internalClassification.category, internalClassification.probability)
    }
}