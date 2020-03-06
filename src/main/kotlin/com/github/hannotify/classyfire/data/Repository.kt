package com.github.hannotify.classyfire.data

import java.nio.file.Path

interface Repository<E> {
    val entities: MutableList<E>

    fun storageLocation(): Path

    fun findAll(): MutableList<E> {
        return entities
    }

    fun save(entity: E) {
        entities.add(entity)
    }

    fun saveAll(entitiesToSave: Collection<E>) {
        entitiesToSave.stream().forEach { save(it) }
    }
}