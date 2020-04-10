package com.github.hannotify.classyfire.process

import com.github.hannotify.classyfire.data.category.Category
import com.github.hannotify.classyfire.data.category.CategoryType
import com.github.hannotify.classyfire.data.classification.Classification
import com.github.hannotify.classyfire.data.classification.ClassificationRepository
import com.github.hannotify.classyfire.data.transaction.Transaction
import de.daslaboratorium.machinelearning.classifier.Classifier
import de.daslaboratorium.machinelearning.classifier.bayes.BayesClassifier
import java.io.File
import java.nio.file.Path

class ClassificationService(private val classificationPath: Path) {
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

    private fun reset() {
        classifier.reset()
    }

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

    fun processTrainingData(categoryType: CategoryType): List<Transaction> {
        val processedTransactions: MutableList<Transaction> = mutableListOf()
        classifier.reset()

        classificationPath.parent.toFile().walk()
                .filter { it.extension == "csv" }
                .map { ClassificationRepository(it.toPath()) }
                .forEach { classificationRepository ->
                    classificationRepository.retrieve()
                    classificationRepository.findAll().stream()
                            .filter { categoryType == it.category.categoryType }
                            .forEach {
                                learn(it.category, it.transaction)
                                processedTransactions.add(it.transaction)
                            }
                }
        println("Trained ${processedTransactions.size} $categoryType transactions.")
        return processedTransactions
    }
}