package com.example.flashcards.ui.deck_list

import com.example.flashcards.models.Deck

interface OnDeckEventListener {
    fun onDeckSelected(id: Long?)
    fun onDeckLongPress(deck: Deck): Boolean
}