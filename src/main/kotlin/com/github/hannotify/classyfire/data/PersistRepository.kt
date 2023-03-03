package com.github.hannotify.classyfire.data

import java.io.File
import java.io.FileOutputStream

interface PersistRepository<E> : Repository<E> {
    fun persist() {
        val targetFile = File(storageLocation().toString())

        if (!targetFile.exists()) {
            targetFile.createNewFile()
        }

        FileOutputStream(targetFile, true).bufferedWriter().use { out ->
            findAll().forEach { out.appendLine(stringFromEntity(it)) }
        }
    }

    fun stringFromEntity(entity: E): String
}
