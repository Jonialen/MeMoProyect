package com.epicapp.memo.navegacion.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.epicapp.memo.ui.theme.topBarColorLight
import com.epicapp.memo.ui.theme.topBarColorDark
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.res.stringResource
import com.epicapp.memo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomNavBar(
    onHeartClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    // Detecta si el tema actual es oscuro o claro
    val isDarkTheme = isSystemInDarkTheme()

    TopAppBar(
        title = { Text(text = "") },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(color = if (isDarkTheme) topBarColorDark else topBarColorLight), // Cambia el color de fondo según el tema
        navigationIcon = {
            IconButton(onClick = onHeartClick) {
                Icon(
                    Icons.Rounded.Favorite,
                    contentDescription = stringResource(R.string.detail_memories),
                    tint = Color.Black
                )
            }
        },
        actions = {
            IconButton(onClick = onProfileClick) {
                Icon(
                    Icons.Filled.Person,
                    contentDescription = stringResource(R.string.profile),
                    tint = Color.Black
                )
            }
        }
    )
}

@Preview
@Composable
fun PreviewCustomNavBar() {
    CustomNavBar(
        onHeartClick = {},
        onProfileClick = {}
    )
}