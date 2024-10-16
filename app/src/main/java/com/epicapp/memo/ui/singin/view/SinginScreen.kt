package com.epicapp.memo.ui.singin.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter // Cambié a rememberAsyncImagePainter
import com.epicapp.memo.ui.allmemories.view.Memory
import com.epicapp.memo.ui.theme.MeMoTheme

@Composable
fun SingIn(
    onLoginClick: () -> Unit,
    onCreateAccountClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Aquí puedes agregar la URL de la imagen que deseas mostrar.
        Image(
            painter = rememberAsyncImagePainter("url_to_logo_image"),
            contentDescription = "App Logo",
            modifier = Modifier.size(150.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = "", // Placeholder for username
            onValueChange = {},
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = "", // Placeholder for username
            onValueChange = {},
            label = { Text("Mail") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = "", // Placeholder for password
            onValueChange = {},
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onLoginClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Next")
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = onCreateAccountClick) {
            Text("Crear nueva cuenta")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DetailMemoryScreenPreview() {
    MeMoTheme{

        SingIn(onLoginClick = { /*TODO*/ }) {
    }

    }
}