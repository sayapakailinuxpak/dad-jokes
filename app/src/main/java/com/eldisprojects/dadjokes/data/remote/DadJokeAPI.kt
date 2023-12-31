package com.eldisprojects.dadjokes.data.remote

import android.content.Context
import com.eldisprojects.dadjokes.data.model.Joke
import com.eldisprojects.dadjokes.data.model.JokeResult
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

interface DadJokeAPI {
    suspend fun fetchRandomDadJoke(): Joke
    fun downloadDadJokeAsImage(context: Context, url: String): Long
    suspend fun searchDadJokes(term: String): JokeResult
    fun copyCurrentJokeToClipboard(context: Context, jokeToCopied: String): Boolean

    suspend fun getJokeById(jokeId: String): Joke

    companion object {
        private val httpClient = HttpClient(engineFactory = Android) {
            install(plugin = ContentNegotiation) {
                json(Json {
                    this.ignoreUnknownKeys = true
                })
            }
        }

        val provideDadJokeAPI: DadJokeAPI = DadJokeApiImpl(httpClient)
    }
}