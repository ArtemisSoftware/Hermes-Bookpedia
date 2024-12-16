package com.plcoding.bookpedia.di

import com.plcoding.bookpedia.book.presentation.SelectedBookViewModel
import com.plcoding.bookpedia.book.presentation.booklist.BookListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::BookListViewModel)
    viewModelOf(::SelectedBookViewModel)
}