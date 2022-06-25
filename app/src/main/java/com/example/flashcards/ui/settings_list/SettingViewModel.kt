package com.example.flashcards.ui.settings_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.flashcards.data.repository.deck.DeckRepository
import com.example.flashcards.data.repository.settings.SettingsRepository
import com.example.flashcards.models.Settings
import com.example.flashcards.models.relationships.SettingsWithDecks

class SettingViewModel(
    val settingsRepository: SettingsRepository
) : ViewModel() {
    val settings = settingsRepository.getAllSettings()

    fun setSettingsWithDeck(settings_id: Long){
        settingsRepository.getSettingsWithDecks(settings_id)
    }

    fun delete(settings: Settings){
        settingsRepository.delete(settings)
    }
}