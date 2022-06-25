package com.example.flashcards.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.flashcards.models.Card
import com.example.flashcards.models.Deck
import com.example.flashcards.models.relationships.DeckWithCards

@Dao
interface DeckDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(deck: Deck)

    @Delete
    fun delete(deck: Deck)

    @Query("SELECT * FROM decks")
    fun getAllDecks(): LiveData<List<Deck>>

    @Query("SELECT * FROM decks WHERE deck_id =:id")
    fun getDeckById(id: Long?): Deck?

    @Transaction
    @Query("SELECT * FROM decks WHERE deck_id =:deck_id")
    fun getDeckWithCards(deck_id: Long): DeckWithCards

    @Query("SELECT EXISTS(SELECT * FROM decks WHERE deck_settings_id =:settings_id)")
    fun isDeckWithSetting(settings_id: Long): Boolean
}