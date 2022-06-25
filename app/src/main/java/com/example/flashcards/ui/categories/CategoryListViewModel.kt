package com.example.flashcards.ui.deck_list


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flashcards.data.repository.card.CategoryRepository
import com.example.flashcards.models.Category

class CategoryListViewModel(
    val categoryRepository: CategoryRepository
) : ViewModel() {
    val categories = categoryRepository.getAllCategories()

    fun delete(category: Category) {
        categoryRepository.delete(category)
    }
}