package com.eldisprojects.dadjokes.data.use_case

import android.util.Log
import com.eldisprojects.dadjokes.data.model.Joke
import com.eldisprojects.dadjokes.data.remote.DadJokeAPI
import com.eldisprojects.dadjokes.data.remote.ResponseState
import com.eldisprojects.dadjokes.data.remote.UIComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

private const val TAG = "SearchDadJokes"
class SearchDadJokes(
    private val jokeAPI: DadJokeAPI
) {
    fun execute(term: String): Flow<ResponseState<List<Joke>>> {
        return flow {
            Log.d(TAG, "execute: Search $term")
            emit(ResponseState.Loading(isLoading = true))
            try {
                val jokeResult = jokeAPI.searchDadJokes(term)
                Log.d(TAG, "execute: emit $jokeResult")
                emit(ResponseState.Success(data = jokeResult.results))
            } catch (e: Exception) {
                Log.d(TAG, "execute: emit $e")
                emit(ResponseState.Error(uiComponent = UIComponent.Toast(e.message.toString())))
            }
        }

    }
}