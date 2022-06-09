package com.example.flashcards.data.repository

import com.example.flashcards.models.Deck

interface DeckRepository {

    fun save(deck: Deck)
    fun delete(deck: Deck)
    fun getAllDecks(): MutableList<Deck>
}