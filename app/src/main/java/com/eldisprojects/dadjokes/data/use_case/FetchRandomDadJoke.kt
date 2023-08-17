package com.eldisprojects.dadjokes.data.use_case

import com.eldisprojects.dadjokes.data.model.Joke
import com.eldisprojects.dadjokes.data.remote.DadJokeAPI
import com.eldisprojects.dadjokes.data.remote.ResponseState
import com.eldisprojects.dadjokes.data.remote.UIComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FetchRandomDadJoke(
    private val dadJokeApi: DadJokeAPI
) {
    fun execute(): Flow<ResponseState<Joke>> {
        return flow {
            emit(value = ResponseState.Loading(true))
            try {
                val joke = dadJokeApi.fetchRandomDadJoke()
                emit(value = ResponseState.Success(joke))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(value = ResponseState.Error(uiComponent = UIComponent.Toast("Something wrong, unable to get joke today ${e.message ?: "Unknown Error"}")))
            } finally {
                emit(value = ResponseState.Loading(false))
            }
        }
    }
}