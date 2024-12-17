package com.plcoding.bookpedia.book.data.network.dto

import com.plcoding.bookpedia.book.data.network.serializer.BookWorkDtoSerializer
import kotlinx.serialization.Serializable

@Serializable(with = BookWorkDtoSerializer::class)
data class BookWorkDto(
    val description: String? = null
)
