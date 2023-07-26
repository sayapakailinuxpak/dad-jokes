package com.eldisprojects.dadjokes.data.use_case

import android.app.DownloadManager
import android.content.Context
import android.graphics.Bitmap
import com.eldisprojects.dadjokes.data.remote.DadJokeAPI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DownloadDadJokeAsImage(private val dadJokeAPI: DadJokeAPI) {
    fun execute(context: Context, jokeId: String) {
        dadJokeAPI.downloadDadJokeAsImage(context, jokeId)
    }
}
