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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomNavBar(
    onHeartClick: () -> Unit,
    onProfileClick: () -> Unit,
    onDotClick: () -> Unit
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
                    contentDescription = "Favorito",
                    tint = Color.Black
                )
            }
        },
        actions = {
            Row(
                modifier = Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onDotClick) {
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .background(Color.Black, CircleShape)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                IconButton(onClick = onProfileClick) {
                    Icon(
                        Icons.Filled.Person,
                        contentDescription = "Perfil",
                        tint = Color.Black
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun PreviewCustomNavBar() {
    CustomNavBar(
        onHeartClick = {},
        onProfileClick = {},
        onDotClick = {}
    )
}
