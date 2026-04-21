package com.github.hannotify.classyfire.process

import kotlin.math.exp
import kotlin.math.ln

class NaiveBayesClassifier<F, C> {

    private val categoryCounts = mutableMapOf<C, Int>()
    private val featureCounts = mutableMapOf<C, MutableMap<F, Int>>()

    data class Result<F, C>(val category: C, val probability: Float)

    fun learn(category: C, features: Collection<F>) {
        categoryCounts[category] = (categoryCounts[category] ?: 0) + 1
        val counts = featureCounts.getOrPut(category) { mutableMapOf() }
        for (feature in features) {
            counts[feature] = (counts[feature] ?: 0) + 1
        }
    }

    fun classify(features: Collection<F>): Result<F, C>? {
        if (categoryCounts.isEmpty()) return null

        val totalExamples = categoryCounts.values.sum().toDouble()
        val vocabulary = featureCounts.values.flatMap { it.keys }.toSet().size

        val logScores = categoryCounts.mapValues { (category, count) ->
            val logPrior = ln(count / totalExamples)
            val featureMap = featureCounts[category] ?: emptyMap()
            val totalFeaturesInCategory = featureMap.values.sum().toDouble()
            val logLikelihood = features.sumOf { feature ->
                val featureCount = (featureMap[feature] ?: 0) + 1
                ln(featureCount / (totalFeaturesInCategory + vocabulary))
            }
            logPrior + logLikelihood
        }

        val winner = logScores.maxByOrNull { it.value }!!
        val probability = softmax(logScores, winner.key)

        return Result(winner.key, probability.toFloat())
    }

    fun reset() {
        categoryCounts.clear()
        featureCounts.clear()
    }

    private fun softmax(logScores: Map<C, Double>, target: C): Double {
        val maxScore = logScores.values.max()
        val expScores = logScores.mapValues { exp(it.value - maxScore) }
        return expScores[target]!! / expScores.values.sum()
    }
}
