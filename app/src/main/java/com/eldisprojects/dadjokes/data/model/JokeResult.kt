package com.eldisprojects.dadjokes.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JokeResult(
    @SerialName("current_page")
    val currentPage: Int,
    val limit: Int,
    @SerialName("next_page")
    val nextPage: Int,
    @SerialName("previous_page")
    val previousPage: Int,
    val results: List<Joke>,
    @SerialName("search_term")
    val searchTerm: String,
    val status: Int,
    @SerialName("total_jokes")
    val totalJokes: Int,
    @SerialName("total_pages")
    val totalPages: Int
)
