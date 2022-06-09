package com.example.flashcards.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flashcards.R
import com.example.flashcards.models.Deck
import com.example.flashcards.ui.deck_list.DeckViewHolder
import com.example.flashcards.ui.deck_list.OnDeckEventListener

class DeckAdapter: RecyclerView.Adapter<DeckViewHolder>(){

    private val decks = mutableListOf<Deck>()
    var onDeckSelectedListener: OnDeckEventListener? = null

    fun setDecks(decks: List<Deck>){
        this.decks.clear()
        this.decks.addAll(decks)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeckViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_deck, parent, false)
        return DeckViewHolder(view)
    }

    override fun onBindViewHolder(holder: DeckViewHolder, position: Int) {
        val deck = decks[position]
        holder.bind(deck)
        onDeckSelectedListener?.let { listener ->
            holder.itemView.setOnClickListener { listener.onDeckSelected(deck.id) }
        }
    }

    override fun getItemCount(): Int = decks.count()

}