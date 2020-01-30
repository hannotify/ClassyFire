package com.github.hannotify.classyfire.data.transaction

import com.github.hannotify.classyfire.data.Repository
import com.github.hannotify.classyfire.data.transaction.TransactionFormat.ING_CSV
import com.github.hannotify.classyfire.data.transaction.retriever.TransactionRetrieverFactory
import java.nio.file.Path

class TransactionRepository(storageLocation: Path,
                            transactionFormat: TransactionFormat = ING_CSV) : Repository<Transaction>(storageLocation) {
    private val transactionCreator = TransactionRetrieverFactory.newTransactionRetriever(transactionFormat)

    override fun isEntityLine(line: String): Boolean {
        return !transactionCreator.isHeaderLine(line)
    }

    override fun entityFromString(string: String): Transaction {
        return transactionCreator.retrieve(string)
    }

    override fun stringFromEntity(entity: Transaction): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}