package com.epicapp.memo.ui.profile.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.epicapp.memo.ui.theme.MeMoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onSaveClick: (String, String) -> Unit = { _, _ -> }
) {
    // Estados para los campos de texto
    val email = remember { "" }
    val password = remember { "" }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Perfil") },
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            // Encabezado con ícono de perfil
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Filled.Person,
                    contentDescription = "Profile Picture",
                    modifier = Modifier.size(100.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
            }

            // Campo: Correo electrónico
            Column {
                Text("Correo electrónico", style = MaterialTheme.typography.titleMedium)
                TextField(
                    value = email,
                    onValueChange = { /* Actualiza el estado */ },
                    placeholder = { Text("Ingrese su correo electrónico") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Campo: Restablecer contraseña
            Column {
                Text("Restablecer contraseña", style = MaterialTheme.typography.titleMedium)
                TextField(
                    value = password,
                    onValueChange = { /* Actualiza el estado */ },
                    placeholder = { Text("Ingrese una nueva contraseña") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Botón: Guardar cambios
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = { onSaveClick(email, password) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar cambios")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    MeMoTheme {
        ProfileScreen()
    }
}
