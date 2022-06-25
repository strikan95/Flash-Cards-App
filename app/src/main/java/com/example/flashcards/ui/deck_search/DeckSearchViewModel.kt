package com.example.flashcards.ui.card_new

import androidx.lifecycle.ViewModel
import com.example.flashcards.data.repository.card.CardRepository
import com.example.flashcards.data.repository.deck.DeckRepository
import com.example.flashcards.models.Card

class DeckSearchViewModel(
    val deckRepository: DeckRepository
) : ViewModel() {

}