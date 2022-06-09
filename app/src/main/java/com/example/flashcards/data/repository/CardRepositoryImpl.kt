package com.example.flashcards.data.repository

import androidx.lifecycle.LiveData
import com.example.flashcards.data.CardDao
import com.example.flashcards.models.Card

class CardRepositoryImpl(val cardDao: CardDao) : CardRepository {
    override fun save(task: Card) = cardDao.save(task)
    override fun delete(task: Card) = cardDao.delete(task)
    override fun getCardById(id: Long): Card? = cardDao.getCardById(id)
    override fun getAllCards(): LiveData<List<Card>> = cardDao.getAllCards()
    override fun getAllCardsFromDeck(deckId: Long): LiveData<List<Card>> = cardDao.getAllCardsFromDeck(deckId)
}