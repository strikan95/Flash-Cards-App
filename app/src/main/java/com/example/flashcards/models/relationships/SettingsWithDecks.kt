package com.example.flashcards.models.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.example.flashcards.models.Deck
import com.example.flashcards.models.Settings

data class SettingsWithDecks(
    @Embedded val settings: Settings,
    @Relation(
        parentColumn = "settings_id",
        entityColumn = "deck_settings_id"
    )
    val deck: List<Deck>
)