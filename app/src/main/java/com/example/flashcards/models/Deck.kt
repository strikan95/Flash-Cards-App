package com.example.flashcards.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.flashcards.utils.DEFAULT_AGAIN_INTERVAL
import com.example.flashcards.utils.DEFAULT_HARD_INTERVAL
import com.example.flashcards.utils.DEFAULT_NUM_OF_NEW_CARDS

@Entity(tableName = "decks")
data class Deck(
    @PrimaryKey(autoGenerate = true)
    var deck_id: Long = 0,
    @ColumnInfo(name = "deck_name")
    var deck_name: String,
    @ColumnInfo(name = "deck_settings_id")
    var deck_settings_id: Long,
    @ColumnInfo(name = "deck_category_id")
    var deck_category_id: Long
)