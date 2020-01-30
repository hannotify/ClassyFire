package com.github.hannotify.classyfire.data.category

import com.github.hannotify.classyfire.data.Repository
import java.nio.file.Path
import java.util.stream.Collectors

class CategoryRepository(storageLocation: Path) : Repository<Category>(storageLocation) {

    fun findIncomeSubcategories(): Collection<Category> {
        return findSubcategoriesByCategoryType(CategoryType.INCOME)
    }

    fun findExpenseSubcategories(): Collection<Category> {
        return findSubcategoriesByCategoryType(CategoryType.EXPENSES)
    }

    private fun findSubcategoriesByCategoryType(categoryType: CategoryType):
            Collection<Category> {
        return findAll().stream()
                .filter { it.isSubcategory() }
                .filter { categoryType == it.categoryType }
                .collect(Collectors.toSet())
    }

    override fun isEntityLine(line: String): Boolean {
        return true
    }

    override fun entityFromString(string: String): Category {
        return Category.fromString(string)
    }

    override fun stringFromEntity(entity: Category): String {
        return entity.toString()
    }
}