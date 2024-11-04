package com.epicapp.memo.navegacion.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.epicapp.memo.ui.theme.MeMoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomNavBar() {
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
            // Icono de estrella con borde
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .padding(8.dp)
            ) {
                Icon(
                    Icons.Rounded.Favorite,
                    contentDescription = "Favorito",
                    tint = Color.Black,
                    modifier = Modifier.fillMaxSize()
                )
            }
        },
        actions = {
            // Punto central (círculo) y el icono de menú
            Row(
                modifier = Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Punto central (círculo)
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(Color.Black, CircleShape)
                )

                Spacer(modifier = Modifier.width(16.dp)) // Espacio entre el círculo y el menú

                // Icono de menú (líneas horizontales)
                Icon(
                    Icons.Filled.Person,
                    contentDescription = "Menú",
                    tint = Color.Black
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun CustomNavBarPreview() {
    MeMoTheme {
        CustomNavBar()
    }
}