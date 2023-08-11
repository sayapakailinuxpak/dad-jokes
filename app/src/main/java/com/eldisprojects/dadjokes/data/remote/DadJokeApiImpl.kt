package com.eldisprojects.dadjokes.data.remote

import android.app.DownloadManager
import android.app.DownloadManager.Request
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.util.Log
import androidx.core.net.toUri
import com.eldisprojects.dadjokes.data.model.Joke
import com.eldisprojects.dadjokes.data.model.JokeResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.path
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val TAG = "DadJokeApiImpl"
class DadJokeApiImpl(
    private val httpClient: HttpClient
) : DadJokeAPI {
    override suspend fun fetchRandomDadJoke(): Joke {
        return withContext(Dispatchers.IO) {
            httpClient.get {
                url("https://icanhazdadjoke.com/")
            }.body<Joke>().also {
                Joke(it.id, it.joke, it.status)
            }
        }
    }

    override fun downloadDadJokeAsImage(context: Context, jokeId: String): Long {
        val downloadManager = context.applicationContext.getSystemService(DownloadManager::class.java)
        val endpoint = "https://icanhazdadjoke.com/j/$jokeId.png"
        val request = DownloadManager.Request(endpoint.toUri())
            .setMimeType("image/png")
            .setAllowedNetworkTypes(Request.NETWORK_WIFI)
            .setAllowedNetworkTypes(Request.NETWORK_MOBILE)
            .setNotificationVisibility(Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setTitle("$jokeId.png")
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, "$jokeId.png")
        return downloadManager.enqueue(request)
    }

    override suspend fun searchDadJokes(term: String): JokeResult {
        Log.d(TAG, "searchDadJokes: Impl $term")
        return withContext(Dispatchers.IO) {
            httpClient.get("https://icanhazdadjoke.com/search") {
                url {
                    parameter("term", term)
                    parameter("limit", 30)
                }
            }.body<JokeResult>()
        }
    }

    override fun copyCurrentJokeToClipboard(context: Context, jokeToCopied: String): Boolean{
        val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData: ClipData = ClipData.newPlainText("Joke Copied", jokeToCopied)
        clipboardManager.setPrimaryClip(clipData)
        return clipboardManager.hasPrimaryClip()
    }
}