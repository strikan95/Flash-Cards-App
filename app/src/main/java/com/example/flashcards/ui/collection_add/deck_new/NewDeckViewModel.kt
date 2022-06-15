package com.example.flashcards.ui.collection_add.deck_new

import androidx.lifecycle.ViewModel
import com.example.flashcards.data.repository.deck.DeckRepository
import com.example.flashcards.models.Deck

class NewDeckViewModel(
    val deckRepository: DeckRepository
) : ViewModel() {
    fun save(deckName: String){
        deckRepository.save(Deck(0, deckName, 1, 1))
    }
}