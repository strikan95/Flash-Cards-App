package com.example.flashcards.ui.deck_list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.flashcards.databinding.ItemDeckBinding
import com.example.flashcards.models.Deck

class DeckViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(deck: Deck){
        val binding = ItemDeckBinding.bind(itemView)
        binding.itemDeckTitle.text = deck.deckName
    }
}