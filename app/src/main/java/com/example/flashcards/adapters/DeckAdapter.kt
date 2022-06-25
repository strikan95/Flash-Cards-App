package com.example.flashcards.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flashcards.R
import com.example.flashcards.models.Category
import com.example.flashcards.models.Deck
import com.example.flashcards.ui.deck_list.DeckViewHolder
import com.example.flashcards.ui.deck_list.OnDeckEventListener

class DeckAdapter: RecyclerView.Adapter<DeckViewHolder>(){

    private val decks = mutableListOf<Deck>()
    private val categories = mutableListOf<Category>()
    var onDeckSelectedListener: OnDeckEventListener? = null

    fun setDecks(decks: List<Deck>){
        this.decks.clear()
        this.decks.addAll(decks)
        this.notifyDataSetChanged()
    }

    fun setCategories(categories: List<Category>){
        this.categories.clear()
        this.categories.addAll(categories)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeckViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_deck, parent, false)
        return DeckViewHolder(view)
    }

    override fun onBindViewHolder(holder: DeckViewHolder, position: Int) {
        val deck = decks[position]
        holder.bind(deck, categories)
        onDeckSelectedListener?.let { listener ->
            holder.itemView.setOnClickListener { listener.onDeckSelected(deck.deck_id) }
            holder.itemView.setOnLongClickListener { listener.onDeckLongPress(deck) }
        }
    }

    override fun getItemCount(): Int = decks.count()

}