package com.example.flashcards.data
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.flashcards.models.Settings
import com.example.flashcards.models.relationships.SettingsWithDecks

@Dao
interface SettingsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(settings: Settings)

    @Delete
    fun delete(settings: Settings)

    @Update
    fun update(settings: Settings)

    @Query("SELECT * FROM settings")
    fun getAllSettings(): LiveData<List<Settings>>

    @Query("SELECT * FROM settings WHERE settings_id =:settings_id")
    fun getSettingsById(settings_id: Long?): Settings?

    @Transaction
    @Query("SELECT * FROM settings WHERE settings_id =:settings_id")
    fun getSettingsWithDeck(settings_id: Long?): SettingsWithDecks

    @Transaction
    @Query("SELECT EXISTS(SELECT * FROM decks WHERE deck_settings_id=:settings_id)")
    fun isSettingInUse(settings_id: Long): Boolean
}