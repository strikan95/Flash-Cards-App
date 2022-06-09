package com.example.flashcards.models.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.example.flashcards.models.Card
import com.example.flashcards.models.Deck

data class DeckWithCards (
    @Embedded val deck: Deck,
    @Relation(
        parentColumn = "id",
        entityColumn = "deck_id"
    )
    val cards: List<Card>
)