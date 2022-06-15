package com.example.flashcards.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.flashcards.utils.DEFAULT_AGAIN_INTERVAL
import com.example.flashcards.utils.DEFAULT_HARD_INTERVAL
import com.example.flashcards.utils.DEFAULT_NUM_OF_NEW_CARDS

@Entity(tableName = "settings")
data class Settings (
    @PrimaryKey(autoGenerate = true)
    var settings_id: Long,
    @ColumnInfo(name = "settings_name")
    var settings_name: String,
)