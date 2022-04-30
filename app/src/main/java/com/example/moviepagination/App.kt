package com.example.moviepagination

import android.app.Application
import com.example.moviepagination.di.application
import com.example.moviepagination.di.movieInfoScreen
import com.example.moviepagination.di.movieListScreen
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
//    companion object {
//        lateinit var instance: App
//    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, movieInfoScreen, movieListScreen))
        }
//        instance = this
        Stetho.initializeWithDefaults(this)
    }
}