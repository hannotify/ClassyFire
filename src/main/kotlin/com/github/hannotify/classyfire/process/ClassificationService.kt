package com.github.hannotify.classyfire.process

import com.github.hannotify.classyfire.data.category.Category
import com.github.hannotify.classyfire.data.classification.Classification
import com.github.hannotify.classyfire.data.classification.ClassificationRepository
import com.github.hannotify.classyfire.data.transaction.Transaction
import de.daslaboratorium.machinelearning.classifier.Classifier
import de.daslaboratorium.machinelearning.classifier.bayes.BayesClassifier
import java.io.File
import java.nio.file.Path

class ClassificationService(classificationPath: Path) {
    private val classifier: Classifier<String, Category> = BayesClassifier()
    private val classificationRepository: ClassificationRepository =
            ClassificationRepository(classificationPath)

    fun saveAndLearn(category: Category, transaction: Transaction) {
        save(category, transaction)
        learn(category, transaction)
    }

    fun classificationCount() = classificationRepository.entities.size
    fun persist() = classificationRepository.persist()
    fun storageLocation() = classificationRepository.storageLocation()

    private fun save(category: Category, transaction: Transaction) {
        classificationRepository.save(Classification(transaction, category))
    }

    internal fun learn(category: Category, transaction: Transaction) {
        classifier.learn(category, transaction.toStringCollection())
    }

    fun classify(transaction: Transaction): Classification? {
        val internalClassification = classifier.classify(transaction.toStringCollection())

        return if (internalClassification == null)
            null
        else
            Classification(transaction, internalClassification.category, internalClassification.probability)
    }

    fun processTrainingData(): List<Transaction> {
        val processedTransactions: MutableList<Transaction> = mutableListOf()
        File("src/test/resources/classifications").walk()
                .filter { it.extension == "csv" }
                .map { ClassificationRepository(it.toPath()) }
                .forEach { classificationRepository ->
                    classificationRepository.retrieve()
                    classificationRepository.findAll().stream()
                            .forEach {
                                learn(it.category, it.transaction)
                                processedTransactions.add(it.transaction)
                            }
                }
        return processedTransactions
    }
}