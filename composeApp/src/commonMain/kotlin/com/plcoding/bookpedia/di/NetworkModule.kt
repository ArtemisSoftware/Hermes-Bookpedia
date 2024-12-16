package com.plcoding.bookpedia.di

import com.plcoding.bookpedia.book.data.network.KtorOpenLibraryDataSource
import com.plcoding.bookpedia.book.data.network.OpenLibraryDataSource
import com.plcoding.bookpedia.core.data.HttpClientFactory
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val networkModule = module {
    single { HttpClientFactory.create(engine = get()) }
    singleOf(::KtorOpenLibraryDataSource).bind<OpenLibraryDataSource>()
}