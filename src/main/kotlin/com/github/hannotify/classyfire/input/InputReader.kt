package com.github.hannotify.classyfire.input

import com.github.hannotify.classyfire.data.transaction.Transaction

interface InputReader {
    fun read(filePath: String): Collection<Transaction>
}
