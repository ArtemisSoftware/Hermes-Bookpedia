package com.plcoding.bookpedia.book.data.network

import com.plcoding.bookpedia.book.data.network.dto.SearchResponseDto
import com.plcoding.bookpedia.core.data.utils.extensions.safeCall
import com.plcoding.bookpedia.core.domain.DataError
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import com.plcoding.bookpedia.core.domain.Result

class KtorOpenLibraryDataSource(
    private val httpClient: HttpClient
): OpenLibraryDataSource {

    override suspend fun searchBooks(
        query: String,
        resultLimit: Int?
    ): Result<SearchResponseDto, DataError.Remote> {
        return safeCall<SearchResponseDto> {
            httpClient.get(
                urlString = "$BASE_URL/search.json"
            ) {
                parameter("q", query)
                parameter("limit", resultLimit)
                parameter("language", "eng")
                parameter("fields", "key,title,author_name,author_key,cover_edition_key,cover_i,ratings_average,ratings_count,first_publish_year,language,number_of_pages_median,edition_count")
            }
        }
    }

//    override suspend fun getBookDetails(bookWorkId: String): Result<BookWorkDto, DataError.Remote> {
//        return safeCall<BookWorkDto> {
//            httpClient.get(
//                urlString = "$BASE_URL/works/$bookWorkId.json"
//            )
//        }
//    }

    private companion object {
        const val BASE_URL = "https://openlibrary.org"
    }
}