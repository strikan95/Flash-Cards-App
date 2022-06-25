package com.example.flashcards.ui.collection_add.deck_new

import androidx.lifecycle.ViewModel
import com.example.flashcards.data.repository.card.CategoryRepository
import com.example.flashcards.data.repository.deck.DeckRepository
import com.example.flashcards.data.repository.settings.SettingsRepository
import com.example.flashcards.models.Category
import com.example.flashcards.models.Deck
import com.example.flashcards.models.Settings

class NewDeckViewModel(
    val deckRepository: DeckRepository,
    val categoryRepository: CategoryRepository,
    val settingsRepository: SettingsRepository
) : ViewModel() {

    var deckName: String = ""

    val categories = categoryRepository.getAllCategories()
    val settings = settingsRepository.getAllSettings()

    val defaultCategory = categoryRepository.getCategoryById(1)
    val defaultSetting = settingsRepository.getSettingsById(1)

    var selectedCategory: Category? = null
    var selectedSetting: Settings? = null

    fun save(){
        if (selectedCategory?.category_id == null){
            selectedCategory = defaultCategory
        }

        if (selectedSetting?.settings_id == null){
            selectedSetting = defaultSetting
        }

        deckRepository.save(Deck(0, deckName, selectedSetting!!.settings_id, selectedCategory!!.category_id))
    }
}