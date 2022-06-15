package com.example.flashcards.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class Category (
    @PrimaryKey(autoGenerate = true)
    var category_id: Long,
    @ColumnInfo(name = "category_name")
    var category_name: String,
    @ColumnInfo(name = "category_color")
    var category_color: String,

)