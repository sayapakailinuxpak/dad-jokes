package com.eldisprojects.dadjokes.presentation.screen.search

import com.eldisprojects.dadjokes.data.model.Joke

data class SearchUiState(
    val jokes: List<Joke> = emptyList(),
    val isLoading: Boolean = false
)
