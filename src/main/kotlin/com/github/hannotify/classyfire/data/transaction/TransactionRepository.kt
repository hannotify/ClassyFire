package com.github.hannotify.classyfire.data.transaction

import com.github.hannotify.classyfire.data.RetrieveRepository
import com.github.hannotify.classyfire.data.category.CategoryType
import com.github.hannotify.classyfire.data.transaction.TransactionFormat.ING_CSV
import com.github.hannotify.classyfire.data.transaction.retriever.TransactionRetrieverFactory
import java.math.BigDecimal
import java.nio.file.Path
import java.util.*
import java.util.stream.Collectors

class TransactionRepository(private val storageLocation: Path,
                            transactionFormat: TransactionFormat = ING_CSV) : RetrieveRepository<Transaction> {
    override val entities: SortedSet<Transaction> = TreeSet()
    private val transactionCreator = TransactionRetrieverFactory.newTransactionRetriever(transactionFormat)

    fun findTransactionsByCategoryType(categoryType: CategoryType): SortedSet<Transaction> {
        val predicate: (Transaction) -> Boolean = when(categoryType) {
            CategoryType.INCOME -> { t -> t.amount > BigDecimal.ZERO }
            CategoryType.EXPENSES -> { t -> t.amount < BigDecimal.ZERO }
        }

        return findTransactionsByPredicate(predicate)
    }

    private fun findTransactionsByPredicate(predicate: (Transaction) -> Boolean):
            SortedSet<Transaction> {
        return findAll().stream()
                .filter { predicate.invoke(it) }
                .collect(Collectors.toCollection { TreeSet<Transaction>() })
    }

    override fun entityFromString(string: String): Transaction = transactionCreator.retrieve(string)
    override fun isEntityLine(line: String): Boolean = !transactionCreator.isHeaderLine(line)
    override fun storageLocation(): Path = storageLocation
}