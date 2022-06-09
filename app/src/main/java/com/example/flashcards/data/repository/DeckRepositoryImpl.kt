package com.example.flashcards.data.repository

import androidx.lifecycle.LiveData
import com.example.flashcards.data.DeckDao
import com.example.flashcards.models.Deck

class DeckRepositoryImpl(val deckDao: DeckDao) : DeckRepository{
    override fun save(deck: Deck) = deckDao.save(deck)

    override fun delete(deck: Deck) = deckDao.delete(deck)

    override fun getDeckById(id: Long?) : Deck? = deckDao.getDeckById(id)

    override fun getAllDecks(): LiveData<List<Deck>> = deckDao.getAllDecks()
}