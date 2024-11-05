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
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomNavBar(
    onHeartClick: () -> Unit,
    onProfileClick: () -> Unit,
    onDotClick: () -> Unit // Agregado para manejar el clic en el punto negro
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFFB2B6DC),
            titleContentColor = Color.Black,
            actionIconContentColor = Color.Black
        ),
        title = {
            Text(text = "")
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
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
                // Punto negro central
                IconButton(onClick = onDotClick) {
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .background(Color.Black, CircleShape)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Icono de perfil
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
