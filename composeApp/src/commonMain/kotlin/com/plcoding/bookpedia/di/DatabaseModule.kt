package com.plcoding.bookpedia.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.plcoding.bookpedia.book.data.database.BookpediaDatabase
import com.plcoding.bookpedia.book.data.database.DatabaseFactory
import org.koin.dsl.module

val databaseModule = module {
    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }

    single {
        get<BookpediaDatabase>().favoriteBookDao
    }

}