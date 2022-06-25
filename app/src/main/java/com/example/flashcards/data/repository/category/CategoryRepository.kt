package com.example.flashcards.data.repository.card

import androidx.lifecycle.LiveData
import com.example.flashcards.models.Card
import com.example.flashcards.models.Category
import com.example.flashcards.models.relationships.CategoryWithDecks
import java.util.*

interface CategoryRepository {
    fun save(category: Category)
    fun delete(category: Category)
    fun update(category: Category)
    fun getCategoryById(category_id: Long): Category?
    fun getAllCategories(): LiveData<List<Category>>
    fun getCategoryWithDecks(category_id: Long): List<CategoryWithDecks>
    fun isCategoryInUse(category_id: Long?): Boolean
}