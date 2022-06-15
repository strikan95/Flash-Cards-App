package com.example.flashcards.data.repository.deck

import androidx.lifecycle.LiveData
import com.example.flashcards.data.DeckDao
import com.example.flashcards.models.Deck
import com.example.flashcards.models.relationships.DeckWithCards

class DeckRepositoryImpl(val deckDao: DeckDao) : DeckRepository {
    override fun save(deck: Deck) = deckDao.save(deck)
    override fun delete(deck: Deck) = deckDao.delete(deck)
    override fun getDeckById(deck_id: Long?) : Deck? = deckDao.getDeckById(deck_id)
    override fun getAllDecks(): LiveData<List<Deck>> = deckDao.getAllDecks()
    override fun getDeckWithCards(deck_id: Long): DeckWithCards = deckDao.getDeckWithCards(deck_id)
}