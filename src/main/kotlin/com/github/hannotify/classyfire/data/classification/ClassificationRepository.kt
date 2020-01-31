package com.github.hannotify.classyfire.data.classification

import com.github.hannotify.classyfire.data.PersistRepository
import com.github.hannotify.classyfire.data.Repository
import com.github.hannotify.classyfire.data.classification.persister.ClassificationPersister
import com.github.hannotify.classyfire.data.classification.persister.ClassificationPersisterFactory
import com.github.hannotify.classyfire.data.classification.persister.IngCsvClassificationPersister
import java.nio.file.Path
import java.util.*

class ClassificationRepository(private val storageLocation: Path) : PersistRepository<Classification> {
    private val classificationPersister = ClassificationPersisterFactory.newClassificationPersister(ClassificationFormat.ING_CSV)

    override fun stringFromEntity(entity: Classification): String = classificationPersister.persist(entity)
    override fun storageLocation(): Path = storageLocation
}
