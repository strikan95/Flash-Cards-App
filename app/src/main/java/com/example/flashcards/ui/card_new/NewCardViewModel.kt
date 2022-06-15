package com.example.flashcards.ui.card_new

import androidx.lifecycle.ViewModel
import com.example.flashcards.data.repository.card.CardRepository
import com.example.flashcards.models.Card

class NewCardViewModel(
    val cardRepository: CardRepository
) : ViewModel() {
    fun save(deckId: Long, title: String, body: String) {
        cardRepository.save(Card(0, title, body, null, deckId))
    }
}