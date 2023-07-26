package com.eldisprojects.dadjokes

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.KeyEventDispatcher.Component
import com.eldisprojects.dadjokes.presentation.screen.splash.SplashScreen
import com.eldisprojects.dadjokes.presentation.ui.theme.DadJokesTheme

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DadJokesTheme {
                SplashScreen()
            }
        }

        Handler(Looper.getMainLooper()).postDelayed({
            Intent(this.applicationContext, MainActivity::class.java).run {
                startActivity(this)
                finish()
            }
        }, 1500)
    }
}