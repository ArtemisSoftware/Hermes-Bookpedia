package com.plcoding.bookpedia.book.presentation.bookdetail

import com.plcoding.bookpedia.book.domain.models.Book

data class BookDetailState(
    val isLoading: Boolean = true,
    val isFavorite: Boolean = false,
    val book: Book? = null
)
