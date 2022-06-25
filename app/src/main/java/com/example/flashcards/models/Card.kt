package com.example.flashcards.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "cards")
data class Card(
    @PrimaryKey(autoGenerate = true)
    var card_id: Long = 0,
    @ColumnInfo(name = "card_title")
    var card_title: String,
    @ColumnInfo(name = "card_body")
    var card_body: String,
    @ColumnInfo(name = "card_last_date_revised")
    var card_last_date_revised: Date?,

    @ColumnInfo(name = "card_deck_id")
    var card_deck_id: Long = 0,
){

}