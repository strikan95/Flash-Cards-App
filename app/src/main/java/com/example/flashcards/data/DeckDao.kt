package com.example.flashcards.data

import androidx.room.*
import com.example.flashcards.models.Deck

@Dao
interface DeckDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(deck: Deck)

    @Delete
    fun delete(deck: Deck)

    @Query("SELECT * FROM decks")
    fun getAllDecks(): MutableList<Deck>
}