package com.example.flashcards.data.repository.settings

import androidx.lifecycle.LiveData
import com.example.flashcards.data.DeckDao
import com.example.flashcards.data.SettingsDao
import com.example.flashcards.data.repository.deck.DeckRepository
import com.example.flashcards.models.Deck
import com.example.flashcards.models.Settings
import com.example.flashcards.models.relationships.DeckWithCards
import com.example.flashcards.models.relationships.SettingsWithDecks

class SettingsRepositoryImpl(val settingsDao: SettingsDao) : SettingsRepository {
    override fun save(settings: Settings) =
        settingsDao.save(settings)

    override fun delete(settings: Settings) =
        settingsDao.delete(settings)

    override fun update(settings: Settings) =
        settingsDao.update(settings)

    override fun getAllSettings(): LiveData<List<Settings>> =
        settingsDao.getAllSettings()

    override fun getSettingsById(settings_id: Long?): Settings? =
        settingsDao.getSettingsById(settings_id)

    override fun getSettingsWithDecks(settings_id: Long?): SettingsWithDecks =
        settingsDao.getSettingsWithDeck(settings_id)

    override fun isSettingInUse(settings_id: Long): Boolean =
        settingsDao.isSettingInUse(settings_id)
}