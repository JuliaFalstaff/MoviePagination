package com.example.moviepagination

import android.app.Application
import com.facebook.stetho.Stetho

class App: Application() {
    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Stetho.initializeWithDefaults(this)
    }
}