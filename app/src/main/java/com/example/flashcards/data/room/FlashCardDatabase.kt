package com.example.flashcards.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.flashcards.data.CardDao
import com.example.flashcards.data.CategoryDao
import com.example.flashcards.data.DeckDao
import com.example.flashcards.data.SettingsDao
import com.example.flashcards.models.Card
import com.example.flashcards.models.Category
import com.example.flashcards.models.Deck
import com.example.flashcards.models.Settings
import java.util.concurrent.Executors


@Database(
    entities = [Card::class, Deck::class, Settings::class, Category::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(FlashCardConverters::class)
abstract class FlashCardDatabase : RoomDatabase() {

    abstract fun getCardDao(): CardDao
    abstract fun getDeckDao(): DeckDao
    abstract fun getSettingsDao(): SettingsDao
    abstract fun getCategoryDao(): CategoryDao

    companion object {

        private const val databaseName = "mDbb.db"

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
                 .addCallback(object :Callback(){
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                       ioThread {
                            INSTANCE!!.getSettingsDao().save(DEFAULT_SETTINGS)
                            INSTANCE!!.getSettingsDao().save(DEFAULT_SETTINGS_1)
                            INSTANCE!!.getSettingsDao().save(DEFAULT_SETTINGS_2)

                            INSTANCE!!.getCategoryDao().save(DEFAULT_CATEGORY)
                            INSTANCE!!.getCategoryDao().save(LANGUAGE_CATEGORY)
                            INSTANCE!!.getCategoryDao().save(SCIENCE_CATEGORY)

                            INSTANCE!!.getDeckDao().save(DEFAULT_DECK)
                            INSTANCE!!.getDeckDao().save(LANGUAGE_DECK_1)
                            INSTANCE!!.getDeckDao().save(LANGUAGE_DECK_2)
                            INSTANCE!!.getDeckDao().save(SCIENCE_DECK_1)
                            INSTANCE!!.getDeckDao().save(SCIENCE_DECK_2)
                            INSTANCE!!.getDeckDao().save(SCIENCE_DECK_3)
                  }
                 }
                  })
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }

        val DEFAULT_SETTINGS = Settings(1, "Default")
        val DEFAULT_SETTINGS_1 = Settings(2, "Settings 1")
        val DEFAULT_SETTINGS_2 = Settings(3, "Settings 2")
        val DEFAULT_CATEGORY = Category(1, "Default", -204)
        val LANGUAGE_CATEGORY = Category(2, "Languages", -16711833)
        val SCIENCE_CATEGORY = Category(3, "Science", -65461)
        val DEFAULT_DECK = Deck(1, "Default Deck 1", 1,1)
        val LANGUAGE_DECK_1 = Deck(2, "English", 1, 2)
        val LANGUAGE_DECK_2 = Deck(3, "German", 1,2)
        val SCIENCE_DECK_1 = Deck(4, "Chemistry", 1,3)
        val SCIENCE_DECK_2 = Deck(5, "Biology", 1,3)
        val SCIENCE_DECK_3 = Deck(6, "Physics", 1,3)

           // .addCallback(object :Callback(){
           //    override fun onCreate(db: SupportSQLiteDatabase) {
            //        super.onCreate(db)
             //       ioThread {
              //          INSTANCE!!.getSettingsDao().save(DEFAULT_SETTINGS)

               //         INSTANCE!!.getCategoryDao().save(DEFAULT_CATEGORY)
               //         INSTANCE!!.getCategoryDao().save(LANGUAGE_CATEGORY)
                 //       INSTANCE!!.getCategoryDao().save(SCIENCE_CATEGORY)

                 //       INSTANCE!!.getDeckDao().save(DEFAULT_DECK)
                  //      INSTANCE!!.getDeckDao().save(LANGUAGE_DECK_1)
                 //       INSTANCE!!.getDeckDao().save(LANGUAGE_DECK_2)
                   //     INSTANCE!!.getDeckDao().save(SCIENCE_DECK_1)
                   //     INSTANCE!!.getDeckDao().save(SCIENCE_DECK_2)
                   //     INSTANCE!!.getDeckDao().save(Companion.SCIENCE_DECK_3)
                  //  }
               // }
          //  })
    }
}
