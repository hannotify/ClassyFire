package com.github.hannotify.classyfire.input

import com.github.hannotify.classyfire.data.transaction.Transaction
import java.nio.file.Path

interface InputReader {
    fun read(inputFilePath: Path): Collection<Transaction>
}
