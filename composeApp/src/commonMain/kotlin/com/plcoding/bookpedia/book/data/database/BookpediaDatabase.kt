package com.plcoding.bookpedia.book.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.plcoding.bookpedia.book.data.database.dao.FavoriteBookDao
import com.plcoding.bookpedia.book.data.database.entities.BookEntity
import com.plcoding.bookpedia.book.data.database.typeconverter.StringListTypeConverter

@Database(
    entities = [BookEntity::class],
    version = 1
)
@TypeConverters(
    StringListTypeConverter::class
)
@ConstructedBy(BookpediaDatabaseConstructor::class)
abstract class BookpediaDatabase: RoomDatabase() {

    abstract val favoriteBookDao: FavoriteBookDao

    companion object {
        const val DB_NAME = "book.db"
    }
}