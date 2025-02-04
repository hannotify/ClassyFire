package com.github.hannotify.classyfire.ui.statemachine

import com.github.hannotify.classyfire.data.category.CategoryRepository
import com.github.hannotify.classyfire.data.transaction.TransactionRepository
import com.github.hannotify.classyfire.process.ClassificationService
import com.github.hannotify.classyfire.testdata.Categories
import com.github.hannotify.classyfire.ui.statemachine.states.*
import io.mockk.every
import io.mockk.mockkStatic
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.io.File
import java.nio.file.Path

internal class StateContextTest {
    private val categoryRepository = CategoryRepository(Path.of("src/test/resources/categories/categories.txt"))
    lateinit var stateContext: StateContext

    @BeforeEach
    internal fun setup() {
        categoryRepository.saveAll(Categories.allCategories)
        categoryRepository.persist()

        stateContext = StateContext(
                CategoryRepository(Path.of("src/test/resources/categories/categories.txt")),
                TransactionRepository(Path.of("src/test/resources/transactions/test.csv")),
                ClassificationService(Path.of("src/test/resources/classifications/test-output.txt")))
    }

    @ParameterizedTest(name = "State {0} should be {1}.")
    @CsvSource(textBlock = """
        0, RetrieveCategoriesState,
        1, RetrieveTransactionsState,
        2, ProcessTrainingDataState
        3, ProcessIncomeTransactionsState,
        4, ProcessTrainingDataState
        5, ProcessExpensesTransactionsState,
        6, PersistClassificationsState""")
    internal fun assertStateOrder(stateNumber: Int, expectedStateName: String) {
        mockkStatic(::readLine)
        every { readLine() } returns "0"

        repeat(stateNumber) { stateContext.nextState() }

        assertThat(stateContext.state?.javaClass?.simpleName).isEqualTo(expectedStateName)
    }

    @Test
    internal fun thereShouldBeNoSeventhState() {
        mockkStatic(::readLine)
        every { readLine() } returns "0"

        repeat(7) { stateContext.nextState() }

        assertThat(stateContext.state).isNull()
    }

    @AfterEach
    internal fun tearDown() {
        File(categoryRepository.storageLocation().toString()).delete()
    }
}