package com.example.flashcards.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.flashcards.data.CardDao
import com.example.flashcards.data.DeckDao
import com.example.flashcards.models.Card
import com.example.flashcards.models.Deck


@Database(
    entities = [Card::class, Deck::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(FlashCardConverters::class)
abstract class FlashCardDatabase : RoomDatabase() {

    abstract fun getCardDao(): CardDao
    abstract fun getDeckDao(): DeckDao

    companion object {

        private const val databaseName = "flashCardDb"

        @Volatile
        private var INSTANCE: FlashCardDatabase? = null

        fun getDatabase(context: Context): FlashCardDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = buildDatabase(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): FlashCardDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                FlashCardDatabase::class.java,
                databaseName
            )
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}