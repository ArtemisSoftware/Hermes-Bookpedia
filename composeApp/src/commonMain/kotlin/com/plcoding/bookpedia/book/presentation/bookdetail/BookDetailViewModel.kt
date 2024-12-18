package com.plcoding.bookpedia.book.presentation.bookdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.plcoding.bookpedia.app.Route
import com.plcoding.bookpedia.book.domain.models.Book
import com.plcoding.bookpedia.book.domain.repository.BookRepository
import com.plcoding.bookpedia.core.domain.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookDetailViewModel(
    private val bookRepository: BookRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val bookId = savedStateHandle.toRoute<Route.BookDetail>().id

    private val _state = MutableStateFlow(BookDetailState())
    val state = _state
        .onStart {
            fetchBookDescription()
            observeFavoriteStatus()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )


    private fun observeFavoriteStatus() {
        bookRepository
            .isBookFavorite(bookId)
            .onEach { isFavorite ->
                _state.update { it.copy(
                    isFavorite = isFavorite
                ) }
            }
            .launchIn(viewModelScope)
    }

    private fun fetchBookDescription() {
        viewModelScope.launch {
            bookRepository
                .getBookDescription(bookId)
                .onSuccess { description ->
                    _state.update { it.copy(
                        book = it.book?.copy(
                            description = description
                        ),
                        isLoading = false
                    ) }
                }
        }
    }

    fun onTriggerEvent(event: BookDetailEvent) {
        when(event) {
            BookDetailEvent.OnFavoriteClick -> updateFavorite()
            is BookDetailEvent.OnSelectedBookChange -> selectBook(event.book)
        }
    }

    private fun selectBook(book: Book) = with(_state){
        update {
            it.copy(book = book)
        }
    }

    private fun updateFavorite() = with(_state.value){
        viewModelScope.launch {
            if(isFavorite) {
                bookRepository.deleteFromFavorites(bookId)
            } else {
                book?.let { book ->
                    bookRepository.markAsFavorite(book)
                }
            }
        }
    }
}