package com.example.flashcards.ui.categories

import com.example.flashcards.models.Category

interface OnCategoryEventListener {
    fun onCategorySelected(category: Category)
    fun onCategoryLongPress(category: Category): Boolean
}