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

    @Update
    fun update(category: Category)

    @Query("SELECT * FROM category")
    fun getAllCategories(): LiveData<List<Category>>

    @Query("SELECT * FROM category WHERE category_id =:category_id")
    fun getCategoryById(category_id: Long?): Category?

    @Transaction
    @Query("SELECT * FROM category WHERE category_id =:category_id")
    fun getCategoryWithDecks(category_id: Long?): List<CategoryWithDecks>

    @Transaction
    @Query("SELECT EXISTS(SELECT * FROM decks WHERE deck_category_id =:category_id)")
    fun isCategoryInUse(category_id: Long?): Boolean
}