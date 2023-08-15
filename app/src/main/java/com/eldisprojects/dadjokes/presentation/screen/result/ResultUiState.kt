package com.eldisprojects.dadjokes.presentation.screen.result

import com.eldisprojects.dadjokes.data.model.Joke

data class ResultUiState(
    val joke: Joke? = null,
    val isLoading: Boolean = false
)