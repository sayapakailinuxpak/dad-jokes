package com.eldisprojects.dadjokes.presentation.screen.home

import android.os.Build
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eldisprojects.dadjokes.R
import com.eldisprojects.dadjokes.data.model.Joke
import com.eldisprojects.dadjokes.data.remote.UIComponent
import com.eldisprojects.dadjokes.presentation.components.ActionButton
import com.eldisprojects.dadjokes.presentation.components.BottomSheet
import com.eldisprojects.dadjokes.presentation.components.DownloadConfirmationDialog
import com.eldisprojects.dadjokes.presentation.components.JokeList
import com.eldisprojects.dadjokes.presentation.components.LoadingBar
import com.eldisprojects.dadjokes.presentation.components.NoDataSearchResultImage
import com.eldisprojects.dadjokes.presentation.components.SearchBar
import com.eldisprojects.dadjokes.presentation.screen.search.SearchUiState
import com.eldisprojects.dadjokes.presentation.screen.search.SearchViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

private const val TAG = "HomeScreen"
@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
fun HomeScreen() {
    val homeViewModel: HomeViewModel = viewModel()
    val searchViewModel: SearchViewModel = viewModel()
    val homeUiState: State<HomeUiState> = homeViewModel.collectAsState()
    val searchUiState: State<SearchUiState> = searchViewModel.collectAsState()
    var openDialog by remember { mutableStateOf(value = false) }
    var searchText by remember { mutableStateOf(value = "") }
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden, confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded})
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()
    var verticalArrangementState: Arrangement.Vertical by remember { mutableStateOf(value = Arrangement.SpaceBetween) }

    BackHandler(sheetState.isVisible) {
        scope.launch {
            sheetState.hide()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
//            .border(width = 1.dp, color = Color.Black)
        ,
        verticalArrangement = verticalArrangementState,
        horizontalAlignment = Alignment.End
    ) {

        SearchBar(
            searchText = searchText,
            valueChange = {
                searchText = it
            },
            focusManager = focusManager,
        )

        if (searchText != "") {
            verticalArrangementState = Arrangement.Top

            LaunchedEffect(key1 = searchText, block = {
                searchViewModel.searchDadJokes(term = searchText)
            })
//            searchViewModel.searchDadJokes(term = searchText)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .border(width = 1.dp, color = Color.Magenta)
                    .padding(top = 24.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (searchUiState.value.jokes.isEmpty()) {
                    NoDataSearchResultImage()
                }
                if (searchUiState.value.isLoading) {
                    LoadingBar()
                } else {
                    JokeList(jokes = searchUiState.value.jokes)
                }

            }

        }

        if (searchText == "") {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .border(1.dp, Color.Black),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.random_joke_title),
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(32.dp))
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
                                if (homeViewModel.copyCurrentJokeToClipboard(context, homeUiState.value.joke?.joke.toString())) {
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
                                openDialog = true
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
                                homeViewModel.fetchRandomDadJoke()
                            }
                        )
                    }
                }
            }

            FloatingActionButton(
                backgroundColor = MaterialTheme.colors.onBackground,
                contentColor = MaterialTheme.colors.background,
                elevation = FloatingActionButtonDefaults.elevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 0.dp,
                    hoveredElevation = 0.dp,
                    focusedElevation = 0.dp
                ),
                onClick = {
                    scope.launch {
                        sheetState.show()
                    }
                },
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .size(40.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.question_icon),
                    contentDescription = null
                )
            }
        }
    }


    BottomSheet(sheetState = sheetState)

    DownloadConfirmationDialog(
        title = "Love the joke?",
        desc = "Confirm to download the joke as image to your device",
        openDialog = openDialog,
        onDismiss = {
            openDialog = false
        },
        onConfirm = {
            homeViewModel.downloadDadJokeAsImage(context, homeUiState.value.joke?.id.toString())
            openDialog = false
        }
    )

    homeViewModel.collectSideEffect(sideEffect = { uiComponent ->
        when (uiComponent) {
            is UIComponent.Toast -> {
                Toast.makeText(context, uiComponent.text, Toast.LENGTH_SHORT).show()
            }
        }
    })

    searchViewModel.collectSideEffect(sideEffect = { uiComponent ->
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