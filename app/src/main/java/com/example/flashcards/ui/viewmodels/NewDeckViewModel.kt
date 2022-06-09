package com.example.flashcards.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.flashcards.data.repository.DeckRepository
import com.example.flashcards.models.Deck

class NewDeckViewModel(
    val deckRepository: DeckRepository
) : ViewModel() {
    fun save(deckName: String){
        deckRepository.save(Deck(0, deckName))
    }
}