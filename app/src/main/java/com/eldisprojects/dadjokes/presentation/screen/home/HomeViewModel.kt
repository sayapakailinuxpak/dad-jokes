package com.eldisprojects.dadjokes.presentation.screen.home

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.eldisprojects.dadjokes.data.remote.DadJokeAPI
import com.eldisprojects.dadjokes.data.remote.DadJokeApiImpl
import com.eldisprojects.dadjokes.data.remote.ResponseState
import com.eldisprojects.dadjokes.data.remote.UIComponent
import com.eldisprojects.dadjokes.data.use_case.CopyCurrentJokeToClipboard
import com.eldisprojects.dadjokes.data.use_case.DownloadDadJokeAsImage
import com.eldisprojects.dadjokes.data.use_case.FetchRandomDadJoke
import com.eldisprojects.dadjokes.data.use_case.GetJokeById
import com.eldisprojects.dadjokes.data.use_case.SearchDadJokes
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import kotlin.math.log

private const val TAG = "HomeViewModel"
class HomeViewModel : ViewModel(), ContainerHost<HomeUiState, UIComponent> {
    //Use cases
    private val fetchRandomDadJoke = FetchRandomDadJoke(DadJokeAPI.provideDadJokeAPI)
    private val copyCurrentJokeToClipboard = CopyCurrentJokeToClipboard(DadJokeAPI.provideDadJokeAPI)
    private val downloadDadJokeAsImage = DownloadDadJokeAsImage(DadJokeAPI.provideDadJokeAPI)
    private val getJokeById = GetJokeById(DadJokeAPI.provideDadJokeAPI)

    override val container: Container<HomeUiState, UIComponent> = container<HomeUiState, UIComponent>(initialState = HomeUiState())
    init {
        fetchRandomDadJoke()
    }

    fun fetchRandomDadJoke() {
        intent {
            val joke = fetchRandomDadJoke.execute()
            viewModelScope.launch() {
                joke.collect() { responseState ->
                    when(responseState) {
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
                                is UIComponent.Toast -> postSideEffect(sideEffect = UIComponent.Toast(text = responseState.uiComponent.text))
                            }
                        }
                    }
                }
            }

        }
    }

    fun copyCurrentJokeToClipboard(context: Context, jokeToCopied: String): Boolean {
        return copyCurrentJokeToClipboard.execute(context, jokeToCopied)
    }

    fun downloadDadJokeAsImage(context: Context, jokeId: String) {
        downloadDadJokeAsImage.execute(context, jokeId)
    }




    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared: ")
    }
}