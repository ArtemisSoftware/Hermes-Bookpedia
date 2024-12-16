package com.plcoding.bookpedia.book.presentation.bookdetail

import com.plcoding.bookpedia.book.domain.models.Book

sealed interface BookDetailEvent {
    data object OnFavoriteClick: BookDetailEvent
    data class OnSelectedBookChange(val book: Book): BookDetailEvent
}