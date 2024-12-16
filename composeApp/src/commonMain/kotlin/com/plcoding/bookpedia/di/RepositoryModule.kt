package com.plcoding.bookpedia.di

import com.plcoding.bookpedia.book.data.repository.BookRepositoryImpl
import com.plcoding.bookpedia.book.domain.repository.BookRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::BookRepositoryImpl).bind<BookRepository>()
}