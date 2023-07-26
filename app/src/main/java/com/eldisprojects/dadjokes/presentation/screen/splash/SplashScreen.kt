package com.eldisprojects.dadjokes.presentation.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eldisprojects.dadjokes.R
import com.eldisprojects.dadjokes.presentation.ui.theme.inconsolata

@Composable
fun SplashScreen(
    onSplashScreenFinished: () -> Unit = {},
) {

    Column(
        modifier = Modifier
            .border(width = 1.dp, color = Color.Black)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Image(painter = painterResource(R.mipmap.funny_face_emoji), contentDescription = null)
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            stringResource(id = R.string.app_name),
            fontFamily = inconsolata,
            fontWeight = FontWeight.Bold,
            fontSize = 36.sp
        )

    }
}



@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Preview() {
    SplashScreen()
}