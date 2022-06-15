package com.example.flashcards.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.flashcards.models.Category
import com.example.flashcards.models.Deck
import com.example.flashcards.models.relationships.CategoryWithDecks
import com.example.flashcards.models.relationships.DeckWithCards

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(category: Category)

    @Delete
    fun delete(category: Category)

    @Query("SELECT * FROM category")
    fun getAllCategories(): List<Category>

    @Query("SELECT * FROM category WHERE category_id =:category_id")
    fun getCategoryById(category_id: Long?): Category?

    @Query("SELECT * FROM category WHERE category_id =:category_id")
    fun getCategoryWithDecks(category_id: Long?): List<CategoryWithDecks>
}