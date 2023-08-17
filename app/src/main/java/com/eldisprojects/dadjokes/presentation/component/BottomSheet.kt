package com.eldisprojects.dadjokes.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eldisprojects.dadjokes.BuildConfig
import com.eldisprojects.dadjokes.R

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheet(sheetState: ModalBottomSheetState, onClick: () -> Unit = {}) {
    ModalBottomSheetLayout(
        sheetContent = {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 24.dp)
            ) {
                Text(
                    text = "${stringResource(id = R.string.app_name)} v${BuildConfig.VERSION_NAME}",
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colors.onSurface
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = AnnotatedString(
                        stringResource(id = R.string.about_description),
                        spanStyles = listOf(
                            AnnotatedString.Range(SpanStyle(fontWeight = FontWeight.Bold, textDecoration = TextDecoration.Underline),46, 52)
                        )
//                        spanStyle = SpanStyle(fontWeight = FontWeight.Bold, textDecoration = TextDecoration.Underline)
                    ),
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 28.sp,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier
                        .padding(bottom = 40.dp)
                        .clickable {
                            onClick()
                        }
                )
                
                

            }

        },
        sheetState = sheetState,
        modifier = Modifier.fillMaxSize(),
        sheetShape = RoundedCornerShape(0.dp)
    ) {
    }
}