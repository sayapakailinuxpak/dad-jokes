package com.eldisprojects.dadjokes.data.use_case

import com.eldisprojects.dadjokes.data.model.Joke
import com.eldisprojects.dadjokes.data.remote.DadJokeAPI
import com.eldisprojects.dadjokes.data.remote.ResponseState
import com.eldisprojects.dadjokes.data.remote.UIComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetJokeById(
    private val dadJokeAPI: DadJokeAPI
) {
   fun execute(jokeId: String): Flow<ResponseState<Joke>> {
        return flow {
            emit(ResponseState.Loading(isLoading = true))
            try {
                val joke = dadJokeAPI.getJokeById(jokeId)
                emit(ResponseState.Success(data = joke))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(ResponseState.Error(uiComponent = UIComponent.Toast(text = e.message.toString())))
            } finally {
                emit(ResponseState.Loading(isLoading = true))
            }
        }
    }
}