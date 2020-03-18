package com.github.hannotify.classyfire.ui.statemachine

import com.github.hannotify.classyfire.data.category.CategoryRepository
import com.github.hannotify.classyfire.data.classification.ClassificationRepository
import com.github.hannotify.classyfire.data.transaction.TransactionRepository
import com.github.hannotify.classyfire.process.ClassificationService
import com.github.hannotify.classyfire.ui.statemachine.states.*
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.nio.file.Path

internal class StateContextTest {
    lateinit var stateContext: StateContext

    @BeforeEach
    internal fun setup() {
        stateContext = StateContext(
                CategoryRepository(Path.of("src/test/resources/categories/categories.txt")),
                TransactionRepository(Path.of("src/test/resources/transactions/test.csv")),
                ClassificationService(Path.of("src/test/resources/classifications/test-output.txt")))
    }

    @Test
    internal fun initialState_shouldBeRetrieveCategoriesState() {
        assertThat(stateContext.state).isInstanceOf(RetrieveCategoriesState::class.java)
    }

    @Test
    internal fun secondState_shouldBeRetrieveTransactionsState() {
        stateContext.nextState()
        assertThat(stateContext.state).isInstanceOf(RetrieveTransactionsState::class.java)
    }

    @Test
    internal fun thirdState_shouldBeProcessIncomeTransactionsState() {
        stateContext.nextState()
        stateContext.nextState()
        assertThat(stateContext.state).isInstanceOf(ProcessIncomeTransactionsState::class.java)
    }

    @Test
    internal fun fourthState_shouldBeProcessExpensesTransactionsState() {
        stateContext.nextState()
        stateContext.nextState()
        stateContext.nextState()
        assertThat(stateContext.state).isInstanceOf(ProcessExpensesTransactionsState::class.java)
    }

    @Test
    internal fun fifthState_shouldBeProcessExpensesTransactionsState() {
        stateContext.nextState()
        stateContext.nextState()
        stateContext.nextState()
        stateContext.nextState()
        assertThat(stateContext.state).isInstanceOf(ProcessExpensesTransactionsState::class.java)
    }

    @Test
    internal fun sixthState_shouldBePersistClassificationsState() {
        stateContext.nextState()
        stateContext.nextState()
        stateContext.nextState()
        stateContext.nextState()
        stateContext.nextState()
        assertThat(stateContext.state).isInstanceOf(PersistClassificationsState::class.java)
    }

    @Test
    internal fun thereShouldBeNoSeventhState() {
        stateContext.nextState()
        stateContext.nextState()
        stateContext.nextState()
        stateContext.nextState()
        stateContext.nextState()
        stateContext.nextState()
        assertThat(stateContext.state).isNull()
    }
}