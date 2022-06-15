package com.example.flashcards.data.repository.card

import androidx.lifecycle.LiveData
import com.example.flashcards.models.Card
import java.util.*

interface CardRepository {
    fun save(card: Card)
    fun delete(card: Card)
    fun getCardById(card_id: Long): Card?
    fun getAllCards(): LiveData<List<Card>>
    fun getAllCardsFromDeck(card_deck_id: Long): List<Card>

    fun updateLastRevised(date: Date?, card_deck_id: Long, card_id: Long)
}