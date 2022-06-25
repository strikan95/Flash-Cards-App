package com.example.flashcards.data.repository.card

import androidx.lifecycle.LiveData
import com.example.flashcards.data.CardDao
import com.example.flashcards.data.CategoryDao
import com.example.flashcards.models.Card
import com.example.flashcards.models.Category
import com.example.flashcards.models.relationships.CategoryWithDecks
import java.util.*

class CategoryRepositoryImpl(val categoryDao: CategoryDao) : CategoryRepository {
    override fun save(category: Category) =
        categoryDao.save(category)

    override fun delete(category: Category) =
        categoryDao.delete(category)

    override fun update(category: Category) =
        categoryDao.update(category)

    override fun getCategoryById(category_id: Long): Category? =
        categoryDao.getCategoryById(category_id)

    override fun getAllCategories(): LiveData<List<Category>> =
        categoryDao.getAllCategories()

    override fun getCategoryWithDecks(category_id: Long): List<CategoryWithDecks> =
        categoryDao.getCategoryWithDecks(category_id)

    override fun isCategoryInUse(category_id: Long?): Boolean = categoryDao.isCategoryInUse(category_id)
}