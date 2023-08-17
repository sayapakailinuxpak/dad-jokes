package com.eldisprojects.dadjokes.presentation.screen.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eldisprojects.dadjokes.data.remote.DadJokeAPI
import com.eldisprojects.dadjokes.data.remote.ResponseState
import com.eldisprojects.dadjokes.data.remote.UIComponent
import com.eldisprojects.dadjokes.data.use_case.SearchDadJokes
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

private const val TAG = "SearchViewModel"

class SearchViewModel : ViewModel(), ContainerHost<SearchUiState, UIComponent>{
    override val container: Container<SearchUiState, UIComponent> = container(initialState = SearchUiState())
    private val searchDadJokes = SearchDadJokes(DadJokeAPI.provideDadJokeAPI)
    @OptIn(FlowPreview::class)
    fun searchDadJokes(term: String) {
        Log.d(TAG, "searchDadJokes: $term")
        intent {
            Log.d(TAG, "searchDadJokesIntent: $term")
            val jokes = searchDadJokes.execute(term)
            viewModelScope.launch {
                Log.d(TAG, "searchDadJokesVMS: $term")
                jokes
                    .collect { responseState ->
                        when(responseState) {
                            is ResponseState.Loading -> {
                                reduce {
                                    Log.d(TAG, "searchDadJokes Loading: $state")
                                    state.copy(isLoading = true)
                                }
                            }
                            is ResponseState.Success -> {
                                reduce {
                                    Log.d(TAG, "searchDadJokes Success: $state")
                                    state.copy(jokes = responseState.data, isLoading = false)
                                }
                            }
                            is ResponseState.Error -> {
                                Log.d(TAG, "searchDadJokes Error: $state")
                                when(responseState.uiComponent) {
                                    is UIComponent.Toast -> postSideEffect(sideEffect = UIComponent.Toast(text = responseState.uiComponent.text))
                                }
                            }
                        }
                    }
            }
        }
    }




}