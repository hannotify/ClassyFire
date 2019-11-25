package com.github.hannotify.classyfire.input

import com.github.hannotify.classyfire.data.Transaction

interface InputReader {
    fun read(filePath: String): Collection<Transaction>
}
