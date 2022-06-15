package com.example.flashcards.data.repository.settings

import com.example.flashcards.models.Settings

interface SettingsRepository {
    fun save(settings: Settings)
    fun delete(settings: Settings)
    fun getSettingsById(settings_id: Long?): Settings?
}