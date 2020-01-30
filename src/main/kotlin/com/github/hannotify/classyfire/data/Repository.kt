package com.github.hannotify.classyfire.data

import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.util.*

abstract class Repository<E>(private val storageLocation: Path) {
    private val entities: SortedSet<E> = TreeSet()

    abstract fun entityFromString(string: String): E
    abstract fun stringFromEntity(entity: E): String

    fun findAll(): Collection<E> {
        return entities;
    }

    abstract fun isEntityLine(line: String): Boolean

    fun persist() {
        File(storageLocation.toString()).printWriter().use { out ->
            findAll().forEach { out.println(stringFromEntity(it)) }
        }
    }

    fun save(entity: E) {
        entities.add(entity)
    }

    fun saveAll(entitiesToSave: Collection<E>) {
        entitiesToSave.stream().forEach { save(it) }
    }

    fun retrieve() {
        Files.lines(storageLocation)
                .filter { isEntityLine(it) }
                .forEach { save(entityFromString(it)) }
    }
}