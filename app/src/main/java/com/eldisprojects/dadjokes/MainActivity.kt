package com.eldisprojects.dadjokes

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eldisprojects.dadjokes.presentation.screen.home.HomeScreen
import com.eldisprojects.dadjokes.presentation.screen.home.HomeViewModel
import com.eldisprojects.dadjokes.presentation.screen.splash.SplashScreen
import com.eldisprojects.dadjokes.presentation.ui.theme.DadJokesTheme
import org.orbitmvi.orbit.compose.collectAsState

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val viewModel by viewModels<HomeViewModel>()
        setContent {
//            val state = viewModel.collectAsState()
            DadJokesTheme {
                // A surface container using the 'background' color from the theme
//                SplashScreen()
//                HomeScreen(viewModel)
                DadJokesApp()
            }
        }
    }
}

@Composable
fun DadJokesApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = DadJokesAppRoute.Home.name
    ) {
        composable(route = DadJokesAppRoute.Home.name) {
            HomeScreen()
        }
    }
}


enum class DadJokesAppRoute {
    Home
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DadJokesTheme {

    }
}