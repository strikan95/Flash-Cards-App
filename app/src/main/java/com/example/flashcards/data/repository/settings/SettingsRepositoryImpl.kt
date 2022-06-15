package com.example.flashcards.data.repository.settings

import androidx.lifecycle.LiveData
import com.example.flashcards.data.DeckDao
import com.example.flashcards.data.SettingsDao
import com.example.flashcards.data.repository.deck.DeckRepository
import com.example.flashcards.models.Deck
import com.example.flashcards.models.Settings
import com.example.flashcards.models.relationships.DeckWithCards

class SettingsRepositoryImpl(val settingsDao: SettingsDao) : SettingsRepository {
    override fun save(settings: Settings) = settingsDao.save(settings)
    override fun delete(settings: Settings) = settingsDao.delete(settings)
    override fun getSettingsById(settings_id: Long?): Settings? =
        settingsDao.getSettingsById(settings_id)
}