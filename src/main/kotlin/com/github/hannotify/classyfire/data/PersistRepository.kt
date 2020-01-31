package com.github.hannotify.classyfire.data

import java.io.File

interface PersistRepository<E> : Repository<E> {
    fun persist() {
        File(storageLocation().toString()).printWriter().use { out ->
            findAll().forEach { out.println(stringFromEntity(it)) }
        }
    }

    fun stringFromEntity(entity: E): String
}