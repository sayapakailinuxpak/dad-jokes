package com.eldisprojects.dadjokes.presentation.screen.home

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.eldisprojects.dadjokes.MainActivity
import com.eldisprojects.dadjokes.R
import com.eldisprojects.dadjokes.data.remote.UIComponent
import com.eldisprojects.dadjokes.data.use_case.DownloadDadJokeAsImage
import com.eldisprojects.dadjokes.presentation.components.ActionButton
import com.eldisprojects.dadjokes.presentation.components.DownloadConfirmationDialog
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

private const val TAG = "HomeScreen"
@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val context = LocalContext.current
    val homeUiState: State<HomeUiState> = viewModel.collectAsState()
    var openDialog = remember { mutableStateOf(value = false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
//            .border(width = 1.dp, color = Color.Black)
        ,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.random_joke_title),
                style = MaterialTheme.typography.subtitle1,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(32.dp))

//            if (!homeUiState.value.isLoading) {
//
//            } else {
//                CircularProgressIndicator(
//                    color = MaterialTheme.colors.onBackground,
//                )
//            }if (homeUiState.value.joke?.joke == null) stringResource(id = R.string.default_random_joke) else "
            if (homeUiState.value.joke?.joke == null) {
                CircularProgressIndicator(
                    color = MaterialTheme.colors.onBackground,
                )
            } else {
                Text(
                    text =  "\"${homeUiState.value.joke?.joke.toString()}\"",
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 28.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(horizontal = 32.dp)
                )
            }



        }
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                ActionButton(
                    iconResource = R.drawable.copy_icon,
                    contentDesc = stringResource(id = R.string.copy_button_content_desc),
                    buttonColor = if (!isSystemInDarkTheme()) colorResource(id = R.color.alice_blue) else colorResource(id = R.color.dodger_blue),
                    iconTintColor = if (isSystemInDarkTheme()) Color.White else null,
                    onClick = {
                        if (viewModel.copyCurrentJokeToClipboard(context, homeUiState.value.joke?.joke.toString())) {
                            if (Build.VERSION.SDK_INT <= 32) {
                                Toast.makeText(context, "Joke Copied", Toast.LENGTH_SHORT).show()
                            }

                        }
                    }
                )
            }
            Column(
                modifier = Modifier.weight(1f)
            ) {
                ActionButton(
                    iconResource = R.drawable.image_icon,
                    contentDesc = stringResource(id = R.string.save_as_image_button_content_desc),
                    buttonColor = if (!isSystemInDarkTheme()) colorResource(id = R.color.mimi_pink) else colorResource(
                        id = R.color.razzmatazz
                    ),
                    iconTintColor = if (isSystemInDarkTheme()) Color.White else null,
                    onClick = {
                        openDialog.value = true
                    }
                )
            }
            Column(
                modifier = Modifier.weight(1f)
            ) {
                ActionButton(
                    iconResource = R.drawable.refresh_icon,
                    contentDesc = stringResource(id = R.string.refresh_button_content_desc),
                    buttonColor = if (!isSystemInDarkTheme()) colorResource(id = R.color.pale_purple) else colorResource(
                        id = R.color.amethyst
                    ),
                    iconTintColor = if (isSystemInDarkTheme()) Color.White else null,
                    onClick = {
                        viewModel.fetchRandomDadJoke()
                    }
                 )
            }
        }
    }

    DownloadConfirmationDialog(
        title = "Love the joke?",
        desc = "Confirm to download the joke as image to your device",
        openDialog = openDialog.value,
        onDismiss = {
            openDialog.value = false
        },
        onConfirm = {
            viewModel.downloadDadJokeAsImage(context, homeUiState.value.joke?.id.toString())
            openDialog.value = false
        }
    )

    viewModel.collectSideEffect(sideEffect = { uiComponent ->
        when (uiComponent) {
            is UIComponent.Toast -> {
                Toast.makeText(context, uiComponent.text, Toast.LENGTH_SHORT).show()
            }
        }
    })



}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview() {
//    HomeScreen()
}