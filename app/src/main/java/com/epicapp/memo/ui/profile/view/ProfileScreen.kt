package com.epicapp.memo.ui.profile.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.Alignment

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Icon(Icons.Filled.Person, contentDescription = "Profile Picture", modifier = Modifier.size(100.dp))

        Spacer(modifier = Modifier.height(16.dp))

        Text("Nombre de usuario", style = MaterialTheme.typography.titleLarge) // Cambié a titleLarge
        TextField(value = "", onValueChange = {}, modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(8.dp))

        Text("Correo electrónico", style = MaterialTheme.typography.titleLarge) // Cambié a titleLarge
        TextField(value = "", onValueChange = {}, modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = { /* Handle password reset */ }) {
            Text("Reset password")
        }
    }
}

