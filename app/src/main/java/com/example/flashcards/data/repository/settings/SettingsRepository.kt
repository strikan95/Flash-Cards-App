package com.example.flashcards.data.repository.settings

import androidx.lifecycle.LiveData
import com.example.flashcards.models.Deck
import com.example.flashcards.models.Settings
import com.example.flashcards.models.relationships.SettingsWithDecks

interface SettingsRepository {
    fun save(settings: Settings)
    fun delete(settings: Settings)
    fun update(settings: Settings)
    fun getAllSettings(): LiveData<List<Settings>>
    fun getSettingsById(settings_id: Long?): Settings?
    fun getSettingsWithDecks(settings_id: Long?): SettingsWithDecks
    fun isSettingInUse(settings_id: Long): Boolean
}