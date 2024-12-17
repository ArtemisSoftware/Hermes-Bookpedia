package com.plcoding.bookpedia.book.data.repository

import com.plcoding.bookpedia.book.data.mappers.toBook
import com.plcoding.bookpedia.book.data.network.OpenLibraryDataSource
import com.plcoding.bookpedia.book.domain.models.Book
import com.plcoding.bookpedia.book.domain.repository.BookRepository
import com.plcoding.bookpedia.core.domain.Result
import com.plcoding.bookpedia.core.domain.DataError
import com.plcoding.bookpedia.core.domain.map

class BookRepositoryImpl(
    private val openLibraryDataSource: OpenLibraryDataSource
//    private val favoriteBookDao: FavoriteBookDao
): BookRepository {
    override suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote> {
        return openLibraryDataSource
            .searchBooks(query)
            .map { dto ->
                dto.results.map { it.toBook() }
            }
    }

    override suspend fun getBookDescription(bookId: String): Result<String?, DataError> {
//        val localResult = favoriteBookDao.getFavoriteBook(bookId)

        return openLibraryDataSource.getBookDetails(bookId).map { it.description }
//        return if(localResult == null) {
//            openLibraryDataSource
//                .getBookDetails(bookId)
//                .map { it.description }
//        } else {
//            Result.Success(localResult.description)
//        }
    }

//    override fun getFavoriteBooks(): Flow<List<Book>> {
//        return favoriteBookDao
//            .getFavoriteBooks()
//            .map { bookEntities ->
//                bookEntities.map { it.toBook() }
//            }
//    }
//
//    override fun isBookFavorite(id: String): Flow<Boolean> {
//        return favoriteBookDao
//            .getFavoriteBooks()
//            .map { bookEntities ->
//                bookEntities.any { it.id == id }
//            }
//    }
//
//    override suspend fun markAsFavorite(book: Book): EmptyResult<DataError.Local> {
//        return try {
//            favoriteBookDao.upsert(book.toBookEntity())
//            Result.Success(Unit)
//        } catch(e: SQLiteException) {
//            Result.Error(DataError.Local.DISK_FULL)
//        }
//    }
//
//    override suspend fun deleteFromFavorites(id: String) {
//        favoriteBookDao.deleteFavoriteBook(id)
//    }
}