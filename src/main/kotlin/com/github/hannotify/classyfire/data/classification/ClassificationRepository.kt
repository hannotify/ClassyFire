package com.github.hannotify.classyfire.data.classification

import com.github.hannotify.classyfire.data.PersistRepository
import com.github.hannotify.classyfire.data.RetrieveRepository
import com.github.hannotify.classyfire.data.classification.persister.ClassificationPersisterFactory
import java.nio.file.Path
import java.util.*
import kotlin.collections.ArrayList

class ClassificationRepository(private val storageLocation: Path) : RetrieveRepository<Classification>, PersistRepository<Classification> {
    override val entities: MutableList<Classification> = ArrayList()
    private val classificationPersister = ClassificationPersisterFactory.newClassificationPersister(ClassificationFormat.ING_CSV)

    override fun stringFromEntity(entity: Classification): String = classificationPersister.persist(entity)
    override fun storageLocation(): Path = storageLocation
    override fun entityFromString(string: String): Classification = Classification.fromString(string)
    override fun isEntityLine(line: String): Boolean = true
}
