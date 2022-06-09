package com.example.flashcards

import android.app.Application
import com.example.flashcards.di.databaseModule
import com.example.flashcards.di.repositoryModule
import com.example.flashcards.di.viewmodelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class FlashCards : Application() {
    override fun onCreate() {
        super.onCreate()
        application = this

        startKoin{
            androidLogger(if(BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@FlashCards)
            modules(
                databaseModule,
                repositoryModule,
                viewmodelModule
            )
        }
    }

    companion object{
        lateinit var application: Application
    }
}