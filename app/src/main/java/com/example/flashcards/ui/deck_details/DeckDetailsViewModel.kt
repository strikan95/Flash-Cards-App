package com.example.flashcards.ui.deck_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flashcards.data.repository.deck.DeckRepository
import com.example.flashcards.models.Deck
import com.example.flashcards.models.relationships.DeckWithCards
import java.util.concurrent.TimeUnit

class DeckDetailsViewModel(
    val deckRepository: DeckRepository
) : ViewModel() {

    private lateinit var _deckWithCards: MutableLiveData<DeckWithCards>

    val timeInDays = TimeUnit.MILLISECONDS.toDays(System.currentTimeMillis())


    fun setDeckWithCards(deck_id: Long){
        _deckWithCards = MutableLiveData<DeckWithCards>()
        _deckWithCards.value = deckRepository.getDeckWithCards(deck_id)
    }
    fun getDeckWithCards() : LiveData<DeckWithCards>{
        return _deckWithCards
    }


    fun getDeckById(id: Long?): Deck?{
        var deck: Deck? = null
        id?.let { deck = deckRepository.getDeckById(id) }
        return deck
    }
}