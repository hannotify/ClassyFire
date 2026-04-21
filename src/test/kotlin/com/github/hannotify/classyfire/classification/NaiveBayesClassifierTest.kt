package com.github.hannotify.classyfire.classification

import com.github.hannotify.classyfire.process.NaiveBayesClassifier
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class NaiveBayesClassifierTest {

    private lateinit var classifier: NaiveBayesClassifier<String, String>

    @BeforeEach
    fun setUp() {
        classifier = NaiveBayesClassifier()
    }

    @Test
    fun classifyReturnsNullWhenUntrained() {
        assertThat(classifier.classify(listOf("feature1", "feature2"))).isNull()
    }

    @Test
    fun classifiesTrainedExampleCorrectly() {
        classifier.learn("sports", listOf("football", "goal", "stadium"))
        classifier.learn("finance", listOf("bank", "interest", "loan"))

        assertThat(classifier.classify(listOf("football", "goal", "stadium"))?.category).isEqualTo("sports")
        assertThat(classifier.classify(listOf("bank", "interest", "loan"))?.category).isEqualTo("finance")
    }

    @Test
    fun prefersTheCategoryWithMoreMatchingTrainingData() {
        classifier.learn("sports", listOf("football", "goal"))
        classifier.learn("sports", listOf("football", "match"))
        classifier.learn("finance", listOf("bank", "loan"))

        assertThat(classifier.classify(listOf("football"))?.category).isEqualTo("sports")
    }

    @Test
    fun resetCausesClassifyToReturnNull() {
        classifier.learn("sports", listOf("football", "goal"))
        classifier.reset()

        assertThat(classifier.classify(listOf("football"))).isNull()
    }

    @Test
    fun probabilityIsWithinValidRange() {
        classifier.learn("sports", listOf("football", "goal"))
        classifier.learn("finance", listOf("bank", "loan"))

        val result = classifier.classify(listOf("football", "goal"))!!
        assertThat(result.probability).isBetween(0.0f, 1.0f)
    }

    @Test
    fun unseenFeaturesDoNotCauseErrors() {
        classifier.learn("sports", listOf("football", "goal"))

        val result = classifier.classify(listOf("completely", "unseen", "features"))
        assertThat(result).isNotNull()
    }

    @Test
    fun winnerHasHigherProbabilityThanAlternatives() {
        classifier.learn("sports", listOf("football", "goal", "match", "player"))
        classifier.learn("finance", listOf("bank", "loan", "interest", "tax"))

        val result = classifier.classify(listOf("football", "goal", "match"))!!
        assertThat(result.category).isEqualTo("sports")
        assertThat(result.probability).isGreaterThan(0.5f)
    }
}
