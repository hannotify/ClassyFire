package com.github.hannotify.classyfire.data

import java.nio.file.Path
import java.util.*

interface Repository<E> {
    val entities: SortedSet<E>

    fun storageLocation(): Path

    fun findAll(): Collection<E> {
        return entities;
    }

    fun save(entity: E) {
        entities.add(entity)
    }

    fun saveAll(entitiesToSave: Collection<E>) {
        entitiesToSave.stream().forEach { save(it) }
    }
}