package com.plcoding.bookpedia.book.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.plcoding.bookpedia.book.data.database.entities.BookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteBookDao {

    @Upsert
    suspend fun upsert(bookEntity: BookEntity)

    @Query("SELECT * FROM BookEntity")
    fun getFavoriteBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM BookEntity WHERE id = :id")
    suspend fun getBook(id: String): BookEntity?

    @Query("DELETE FROM BookEntity WHERE id = :id")
    suspend fun deleteBook(id: String)
}