package com.example.flashcards.data.room

import androidx.room.TypeConverter
import java.util.*

class FlashCardConverters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }
}