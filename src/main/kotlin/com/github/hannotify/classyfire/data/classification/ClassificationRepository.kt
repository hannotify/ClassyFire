package com.github.hannotify.classyfire.data.classification

import com.github.hannotify.classyfire.data.Repository
import com.github.hannotify.classyfire.data.classification.persister.ClassificationPersister
import com.github.hannotify.classyfire.data.classification.persister.ClassificationPersisterFactory
import com.github.hannotify.classyfire.data.classification.persister.IngCsvClassificationPersister
import java.nio.file.Path

class ClassificationRepository(storageLocation: Path) : Repository<Classification>(storageLocation) {
    private val classificationPersister = ClassificationPersisterFactory.newClassificationPersister(ClassificationFormat.ING_CSV)

    override fun isEntityLine(line: String): Boolean {
        return true
    }

    override fun entityFromString(string: String): Classification {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun stringFromEntity(entity: Classification): String {
        return classificationPersister.persist(entity)
    }
}