package com.example.flashcards.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "cards")
data class Card(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "body")
    var body: String,
    //@ColumnInfo(name = "lastDateRevised")
    //var lastDateRevised: Date
){

}