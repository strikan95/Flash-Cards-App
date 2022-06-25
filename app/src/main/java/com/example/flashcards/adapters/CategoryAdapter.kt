package com.example.flashcards.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flashcards.R
import com.example.flashcards.models.Category
import com.example.flashcards.ui.categories.CategoryViewHolder
import com.example.flashcards.ui.categories.OnCategoryEventListener

class CategoryAdapter: RecyclerView.Adapter<CategoryViewHolder>(){

    private val categories = mutableListOf<Category>()
    var onCategorySelectedListener: OnCategoryEventListener? = null

    fun setCategories(categories: List<Category>){
        this.categories.clear()
        this.categories.addAll(categories)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category_card, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.bind(category)
        onCategorySelectedListener?.let { listener ->
            holder.itemView.setOnClickListener { listener.onCategorySelected(category) }
            holder.itemView.setOnLongClickListener { listener.onCategoryLongPress(category) }
        }
    }

    override fun getItemCount(): Int = categories.count()

}