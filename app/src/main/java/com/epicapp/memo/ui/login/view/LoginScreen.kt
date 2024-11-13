package com.epicapp.memo.ui.login.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.epicapp.memo.R

@Composable
fun LoginScreen(
    onLoginClick: () -> Unit,
    onCreateAccountClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo de la aplicación
        Image(
            painter = painterResource(id = R.drawable.logo_memo),
            contentDescription = "App Logo",
            modifier = Modifier
                .size(400.dp)
                .padding(bottom = 32.dp)
        )

        // Título o texto de bienvenida
        Text(
            text = "Welcome to MEMO",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Campos de entrada (ejemplo de campo de usuario y contraseña)
        OutlinedTextField(
            value = "",
            onValueChange = { /* TODO */ },
            label = { Text("Username") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = "",
            onValueChange = { /* TODO */ },
            label = { Text("Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // Botón de inicio de sesión
        Button(
            onClick = onLoginClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }

        // Botón para crear cuenta
        TextButton(onClick = onCreateAccountClick) {
            Text("Create an Account")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        onLoginClick = {},
        onCreateAccountClick = {}
    )
}
