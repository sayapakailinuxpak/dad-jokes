package com.eldisprojects.dadjokes.presentation.screen.home

import com.eldisprojects.dadjokes.data.model.Joke

data class HomeUiState(
    val isLoading: Boolean = false,
    val joke: Joke? = null
)