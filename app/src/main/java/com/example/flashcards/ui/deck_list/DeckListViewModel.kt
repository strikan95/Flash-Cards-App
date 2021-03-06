package com.example.flashcards.ui.deck_list

import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Color
import android.util.Log
import androidx.core.graphics.toColorInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flashcards.R
import com.example.flashcards.data.repository.card.CategoryRepository
import com.example.flashcards.data.repository.deck.DeckRepository
import com.example.flashcards.models.Category
import com.example.flashcards.models.Deck
import com.example.flashcards.models.relationships.DeckWithCards
import com.google.android.material.chip.Chip
import java.util.*

class DeckListViewModel(
    val deckRepository: DeckRepository,
    val categoryRepository: CategoryRepository
) : ViewModel() {
    val decks = deckRepository.getAllDecks()
    val categories = categoryRepository.getAllCategories()

    private var _currentCategory = MutableLiveData<Category>()
    val currentCategory: LiveData<Category> = _currentCategory


    fun setCurrentCategory(id: Long){
        _currentCategory.value = categoryRepository.getCategoryById(id)
    }

    fun delete(deck: Deck) {
        deckRepository.delete(deck)
    }
}