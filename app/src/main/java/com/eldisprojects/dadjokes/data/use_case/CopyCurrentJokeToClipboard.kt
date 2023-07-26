package com.eldisprojects.dadjokes.data.use_case

import android.content.Context
import com.eldisprojects.dadjokes.data.remote.DadJokeAPI
import com.eldisprojects.dadjokes.data.remote.ResponseState
import com.eldisprojects.dadjokes.data.remote.UIComponent

class CopyCurrentJokeToClipboard(private val dadJokeAPI: DadJokeAPI) {
    fun execute(context: Context, jokeToCopied: String): Boolean {
        return dadJokeAPI.copyCurrentJokeToClipboard(context, jokeToCopied)
    }
}