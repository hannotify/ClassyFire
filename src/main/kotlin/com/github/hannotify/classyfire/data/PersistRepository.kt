package com.github.hannotify.classyfire.data

import java.io.File
import java.io.FileOutputStream

interface PersistRepository<E> : Repository<E> {
    fun persist() {
        FileOutputStream(File(storageLocation().toString()), true).bufferedWriter().use { out ->
            findAll().forEach { out.appendln(stringFromEntity(it)) }
        }
    }

    fun stringFromEntity(entity: E): String
}