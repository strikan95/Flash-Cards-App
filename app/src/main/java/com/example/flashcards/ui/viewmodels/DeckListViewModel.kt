package com.example.flashcards.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.flashcards.data.repository.DeckRepository
import com.example.flashcards.models.Deck

class DeckListViewModel(
    val deckRepository: DeckRepository
) : ViewModel() {
    val decks = deckRepository.getAllDecks()

    fun delete(deck: Deck){
        deckRepository.delete(deck)
    }
}