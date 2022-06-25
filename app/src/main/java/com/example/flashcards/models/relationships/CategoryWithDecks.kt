package com.example.flashcards.models.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.example.flashcards.models.Category
import com.example.flashcards.models.Deck
import com.example.flashcards.models.Settings

data class CategoryWithDecks(
    @Embedded val category: Category,
    @Relation(
        parentColumn = "category_id",
        entityColumn = "deck_category_id"
    )
    val deck: List<Deck>
)