package com.plcoding.bookpedia

import android.app.Application
import com.plcoding.bookpedia.di.initKoin
import org.koin.android.ext.koin.androidContext

class BookpediaApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@BookpediaApplication)
        }
    }
}