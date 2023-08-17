package com.eldisprojects.dadjokes.data.use_case

import android.content.Context
import com.eldisprojects.dadjokes.data.remote.DadJokeAPI

class DownloadDadJokeAsImage(private val dadJokeAPI: DadJokeAPI) {
    fun execute(context: Context, jokeId: String) {
        dadJokeAPI.downloadDadJokeAsImage(context, jokeId)
    }
}
