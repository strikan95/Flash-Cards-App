package com.example.flashcards.di

import android.app.Application
import com.example.flashcards.data.CardDao
import com.example.flashcards.data.DeckDao
import com.example.flashcards.data.repository.CardRepository
import com.example.flashcards.data.repository.CardRepositoryImpl
import com.example.flashcards.data.repository.DeckRepository
import com.example.flashcards.data.repository.DeckRepositoryImpl
import com.example.flashcards.data.room.FlashCardDatabase
import com.example.flashcards.ui.viewmodels.DeckDetailsViewModel
import com.example.flashcards.ui.viewmodels.DeckListViewModel
import com.example.flashcards.ui.viewmodels.NewCardViewModel
import com.example.flashcards.ui.viewmodels.NewDeckViewModel
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

    single<FlashCardDatabase> { provideDatabase(get()) }
    single<CardDao> { provideCardDao(get()) }
    single<DeckDao> { provideDeckDao(get()) }
}

val repositoryModule = module {
    single<CardRepository> { CardRepositoryImpl(get()) }
    single<DeckRepository> { DeckRepositoryImpl(get()) }
}

val viewmodelModule = module {
    viewModel { NewCardViewModel(get())  }
    viewModel { NewDeckViewModel(get())  }
    viewModel { DeckListViewModel(get()) }
    viewModel { DeckDetailsViewModel(get()) }

}