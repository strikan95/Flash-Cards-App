package com.example.flashcards.di

import android.app.Application
import com.example.flashcards.data.CardDao
import com.example.flashcards.data.CategoryDao
import com.example.flashcards.data.DeckDao
import com.example.flashcards.data.repository.card.CardRepository
import com.example.flashcards.data.repository.card.CardRepositoryImpl
import com.example.flashcards.data.repository.card.CategoryRepository
import com.example.flashcards.data.repository.card.CategoryRepositoryImpl
import com.example.flashcards.data.repository.deck.DeckRepository
import com.example.flashcards.data.repository.deck.DeckRepositoryImpl
import com.example.flashcards.data.repository.settings.SettingsRepository
import com.example.flashcards.data.repository.settings.SettingsRepositoryImpl
import com.example.flashcards.data.room.FlashCardDatabase
import com.example.flashcards.ui.card_new.NewCardViewModel
import com.example.flashcards.ui.collection_add.deck_new.NewDeckViewModel
import com.example.flashcards.ui.deck_details.DeckDetailsViewModel
import com.example.flashcards.ui.deck_list.DeckListViewModel
import com.example.flashcards.ui.deck_study.DeckStudyViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val databaseModule = module {
    fun provideDatabase(application: Application): FlashCardDatabase {
        return FlashCardDatabase.getDatabase(application)
    }
    fun provideDeckDao(database: FlashCardDatabase): DeckDao{
        return database.getDeckDao()
    }

    fun provideCardDao(database: FlashCardDatabase): CardDao{
        return database.getCardDao()
    }

    fun provideCategoryDao(database: FlashCardDatabase): CategoryDao{
        return database.getCategoryDao()
    }

    single<FlashCardDatabase> { provideDatabase(get()) }
    single<CardDao> { provideCardDao(get()) }
    single<DeckDao> { provideDeckDao(get()) }
    single<CategoryDao> {provideCategoryDao(get())}
}

val repositoryModule = module {
    single<CardRepository> { CardRepositoryImpl(get()) }
    single<DeckRepository> { DeckRepositoryImpl(get()) }
    single<SettingsRepository> { SettingsRepositoryImpl(get()) }
    single<CategoryRepository> { CategoryRepositoryImpl(get()) }
}

val viewmodelModule = module {
    viewModel { NewCardViewModel(get())  }
    viewModel { NewDeckViewModel(get())  }
    viewModel { DeckListViewModel(get(), get()) }
    viewModel { DeckDetailsViewModel(get()) }
    viewModel { DeckStudyViewModel(get(), get()) }

}