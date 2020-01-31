package com.github.hannotify.classyfire.data

import java.nio.file.Files

interface RetrieveRepository<E> : Repository<E> {
    fun retrieve() {
        Files.lines(storageLocation())
                .filter { isEntityLine(it) }
                .forEach { save(entityFromString(it)) }
    }

    fun entityFromString(string: String): E
    fun isEntityLine(line: String): Boolean
}