package com.example.flashcards.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.flashcards.models.Card
import java.util.*

@Dao
interface CardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(card: Card)

    @Delete
    fun delete(card: Card)

    @Query("SELECT * FROM cards WHERE card_id =:card_id")
    fun getCardById(card_id: Long): Card?

    @Query("SELECT * FROM cards")
    fun getAllCards(): LiveData<List<Card>>

    @Query("SELECT * FROM cards WHERE card_deck_id =:card_deck_id")
    fun getAllCardsFromDeck(card_deck_id: Long): List<Card>

    @Query("UPDATE cards SET card_last_date_revised =:date WHERE (card_deck_id =:card_deck_id AND card_id =:card_id)")
    fun updateLastRevised(date: Date?, card_deck_id: Long, card_id: Long)
}