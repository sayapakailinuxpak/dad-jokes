package com.eldisprojects.dadjokes.presentation.screen.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eldisprojects.dadjokes.data.remote.DadJokeAPI
import com.eldisprojects.dadjokes.data.remote.ResponseState
import com.eldisprojects.dadjokes.data.remote.UIComponent
import com.eldisprojects.dadjokes.data.use_case.GetJokeById
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ResultViewModel : ViewModel(), ContainerHost<ResultUiState, UIComponent> {
    override val container: Container<ResultUiState, UIComponent> = container(initialState = ResultUiState())
    private val getJokeById = GetJokeById(DadJokeAPI.provideDadJokeAPI)

    fun getJokeById(jokeId: String) {
        intent {
            viewModelScope.launch {
                getJokeById.execute(jokeId)
                    .collect { responseState ->
                        when (responseState) {
                            is ResponseState.Loading -> {
                                reduce {
                                    state.copy(isLoading = true)
                                }
                            }

                            is ResponseState.Success -> {
                                reduce {
                                    state.copy(joke = responseState.data)
                                }
                            }

                            is ResponseState.Error -> {
                                when (responseState.uiComponent) {
                                    is UIComponent.Toast -> postSideEffect(UIComponent.Toast(text = responseState.uiComponent.text))
                                }
                            }
                        }
                    }
            }
        }
    }
}