package com.example.flashcards.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.flashcards.models.Card

@Dao
interface CardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(card: Card)

    @Delete
    fun delete(card: Card)

    @Query("SELECT * FROM cards WHERE id =:id")
    fun getCardById(id: Long): Card?

    @Query("SELECT * FROM cards")
    fun getAllCards(): LiveData<List<Card>>
}