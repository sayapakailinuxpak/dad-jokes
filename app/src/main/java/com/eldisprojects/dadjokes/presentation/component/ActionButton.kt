package com.eldisprojects.dadjokes.presentation.components

import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.ElevationOverlay
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eldisprojects.dadjokes.R
import com.eldisprojects.dadjokes.presentation.ui.theme.Shapes

@Composable
fun ActionButton(
    iconResource: Int,
    contentDesc: String,
    buttonColor: Color,
    iconTintColor: Color? = null,
    onClick: () -> Unit = {}
) {
    Button(
        shape = Shapes.small,
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp,
            hoveredElevation = 0.dp,
            focusedElevation = 0.dp
        ),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = buttonColor
        ),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
    ) {
        Image(
            painter = painterResource(id = iconResource),
            contentDescription = contentDesc,
            colorFilter = iconTintColor?.let { ColorFilter.tint(it) }
        )
    }
}
