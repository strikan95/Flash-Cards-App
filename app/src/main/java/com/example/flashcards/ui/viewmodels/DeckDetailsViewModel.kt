package com.example.flashcards.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.flashcards.data.repository.DeckRepository
import com.example.flashcards.models.Deck

class DeckDetailsViewModel(
    val deckRepository: DeckRepository
) : ViewModel() {
    fun getDeckById(id: Long?): Deck?{
        var deck: Deck? = null
        id?.let { deck = deckRepository.getDeckById(id) }
        return deck
    }
}