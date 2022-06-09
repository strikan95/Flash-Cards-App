package com.example.flashcards.data.repository

import androidx.lifecycle.LiveData
import com.example.flashcards.models.Deck

interface DeckRepository {

    fun save(deck: Deck)
    fun delete(deck: Deck)
    fun getDeckById(id: Long?): Deck?
    fun getAllDecks(): LiveData<List<Deck>>
}