package com.example.flashcards.ui.categories.category_add

import androidx.lifecycle.ViewModel
import com.example.flashcards.data.repository.card.CategoryRepository
import com.example.flashcards.models.Category

class CategoryBottomSheetViewModel(
    val categoryRepository: CategoryRepository
) : ViewModel() {

    var categoryName: String = ""

    fun save(categoryColor: Int){
        categoryRepository.save(Category(0, categoryName, categoryColor))
    }

    fun save(categoryName: String, categoryColor: Int){
        categoryRepository.save(Category(0,categoryName, categoryColor))
    }
    fun update(category: Category) {
        categoryRepository.update(category)
    }

}