package com.example.flashcards.data.repository

import com.example.flashcards.data.DeckDao
import com.example.flashcards.models.Deck

class DeckRepositoryImpl(val deckDao: DeckDao) : DeckRepository{
    override fun save(deck: Deck) = deckDao.save(deck)

    override fun delete(deck: Deck) = deckDao.delete(deck)

    override fun getAllDecks(): MutableList<Deck> = deckDao.getAllDecks()
}