package com.example.flashcards.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.flashcards.models.Deck

@Dao
interface DeckDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(deck: Deck)

    @Delete
    fun delete(deck: Deck)

    @Query("SELECT * FROM decks WHERE id =:id")
    fun getDeckById(id: Long?): Deck?

    @Query("SELECT * FROM decks")
    fun getAllDecks(): LiveData<List<Deck>>
}