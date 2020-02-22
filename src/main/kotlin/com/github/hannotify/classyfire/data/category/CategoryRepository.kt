package com.github.hannotify.classyfire.data.category

import com.github.hannotify.classyfire.data.PersistRepository
import com.github.hannotify.classyfire.data.RetrieveRepository
import java.nio.file.Path
import java.util.*
import java.util.stream.Collectors

class CategoryRepository(private val storageLocation: Path) : RetrieveRepository<Category>, PersistRepository<Category> {
    override val entities: SortedSet<Category> = TreeSet()

    fun findSubcategoriesByCategoryType(categoryType: CategoryType):
            Collection<Category> {
        return findAll().stream()
                .filter { it.isSubcategory() }
                .filter { categoryType == it.categoryType }
                .collect(Collectors.toSet())
    }

    override fun entityFromString(string: String): Category = Category.fromString(string)
    override fun isEntityLine(line: String): Boolean = true
    override fun storageLocation(): Path = storageLocation
    override fun stringFromEntity(entity: Category): String = entity.toString()
}