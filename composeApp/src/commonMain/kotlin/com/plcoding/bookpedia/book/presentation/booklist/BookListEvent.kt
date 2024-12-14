package com.plcoding.bookpedia.book.presentation.booklist

import com.plcoding.bookpedia.book.domain.models.Book

sealed interface BookListEvent {
    data class OnSearchQueryChange(val query: String): BookListEvent
    data class OnBookClick(val book: Book): BookListEvent
    data class OnTabSelected(val index: Int): BookListEvent
}