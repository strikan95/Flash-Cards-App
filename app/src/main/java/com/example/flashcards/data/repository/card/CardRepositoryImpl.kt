package com.example.flashcards.data.repository.card

import androidx.lifecycle.LiveData
import com.example.flashcards.data.CardDao
import com.example.flashcards.models.Card
import java.util.*

class CardRepositoryImpl(val cardDao: CardDao) : CardRepository {
    // BASE
    override fun save(card: Card) = cardDao.save(card)
    override fun delete(card: Card) = cardDao.delete(card)

    // SEARCHES
    override fun getCardById(card_id: Long): Card? =
        cardDao.getCardById(card_id)

    override fun getAllCards(): LiveData<List<Card>> =
        cardDao.getAllCards()

    override fun getAllCardsFromDeck(card_deck_id: Long): List<Card> =
        cardDao.getAllCardsFromDeck(card_deck_id)

    // UPDATES
    override fun updateLastRevised(date: Date?, card_deck_id: Long, card_id: Long) =
        cardDao.updateLastRevised(date, card_deck_id, card_id)
}