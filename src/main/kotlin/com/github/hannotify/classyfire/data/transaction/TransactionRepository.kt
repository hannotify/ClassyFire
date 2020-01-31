package com.github.hannotify.classyfire.data.transaction

import com.github.hannotify.classyfire.data.RetrieveRepository
import com.github.hannotify.classyfire.data.transaction.TransactionFormat.ING_CSV
import com.github.hannotify.classyfire.data.transaction.retriever.TransactionRetrieverFactory
import java.nio.file.Path

class TransactionRepository(private val storageLocation: Path,
                            transactionFormat: TransactionFormat = ING_CSV) : RetrieveRepository<Transaction> {
    private val transactionCreator = TransactionRetrieverFactory.newTransactionRetriever(transactionFormat)

    override fun entityFromString(string: String): Transaction = transactionCreator.retrieve(string)
    override fun isEntityLine(line: String): Boolean = !transactionCreator.isHeaderLine(line)
    override fun storageLocation(): Path = storageLocation
}