package com.plcoding.bookpedia.book.data.database

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object BookpediaDatabaseConstructor: RoomDatabaseConstructor<BookpediaDatabase> {
    override fun initialize(): BookpediaDatabase
}