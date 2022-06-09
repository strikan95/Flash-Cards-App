package com.example.flashcards.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.flashcards.models.Card

class CardViewModel : ViewModel(){
    private var cardList: MutableList<Card> = mutableListOf()

    private lateinit var _currentCard: Card
    var currentCard: Card? = null

    init {
        cardList.add(0, Card(0, 0,"Card 1", "answer 1"))
        cardList.add(1, Card(1, 0,"card 2", "answer 2"))
        cardList.add(2, Card(2, 0,"card 3", "answer 3"))
        cardList.add(3, Card(3, 0,"card 4", "answer 4"))
        cardList.add(4, Card(4, 0,"card 5", "answer 5"))
        cardList.add(5, Card(5, 0,"card 6", "answer 6"))
    }

    private fun loadCard(){
        if(cardList.isNotEmpty()) {
            _currentCard = cardList.first()
            cardList.removeFirst()
            currentCard = _currentCard
        }else{
            currentCard = null
        }
    }

    fun getNextCard() : Card?{
        loadCard()
        return currentCard
    }
}