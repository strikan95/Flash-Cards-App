package com.example.flashcards.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.flashcards.data.repository.CardRepository
import com.example.flashcards.models.Card

class NewCardViewModel(
    val cardRepository: CardRepository
) : ViewModel() {
    fun save(title: String, body: String) {
        cardRepository.save(Card(0, title, body))
    }
}