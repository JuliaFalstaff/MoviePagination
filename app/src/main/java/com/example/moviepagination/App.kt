package com.example.moviepagination

import android.app.Application
import com.example.moviepagination.di.*
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, movieInfoScreen, movieListScreen, actorInfoScreen, searchResultScreen, top250Screen))
        }
        Stetho.initializeWithDefaults(this)
    }
}