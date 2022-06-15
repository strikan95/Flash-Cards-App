package com.example.flashcards.data
import androidx.room.*
import com.example.flashcards.models.Settings

@Dao
interface SettingsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(settings: Settings)

    @Delete
    fun delete(settings: Settings)

    @Query("SELECT * FROM settings")
    fun getSettingsById(): Settings?

    @Query("SELECT * FROM settings WHERE settings_id =:settings_id")
    fun getSettingsById(settings_id: Long?): Settings?
}