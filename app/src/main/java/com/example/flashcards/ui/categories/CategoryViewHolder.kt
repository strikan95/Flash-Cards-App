package com.example.flashcards.ui.categories

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.flashcards.databinding.ItemCategoryCardBinding
import com.example.flashcards.databinding.ItemDeckBinding
import com.example.flashcards.models.Category
import com.example.flashcards.models.Deck

class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(category: Category){
        val binding = ItemCategoryCardBinding.bind(itemView)
        binding.itemDeckTitle.text = category.category_name
        binding.categoryCardColorSidebar.setBackgroundColor(category.category_color)
    }
}