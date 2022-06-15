package com.example.flashcards.data.repository.deck

import androidx.lifecycle.LiveData
import com.example.flashcards.models.Deck
import com.example.flashcards.models.relationships.DeckWithCards

interface DeckRepository {
    fun save(deck: Deck)
    fun delete(deck: Deck)
    fun getDeckById(deck_id: Long?): Deck?
    fun getAllDecks(): LiveData<List<Deck>>
    fun getDeckWithCards(deck_id: Long): DeckWithCards
}