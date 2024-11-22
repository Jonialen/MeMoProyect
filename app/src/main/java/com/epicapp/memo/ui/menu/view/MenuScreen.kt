package com.epicapp.memo.ui.menu.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.epicapp.memo.R
import com.epicapp.memo.ui.theme.MeMoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
    onProfileClick: () -> Unit,
    onExportClick: () -> Unit,
    onImportClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.menu)) }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.Start
        ) {
            // Botón para perfil
            Button(
                onClick = onProfileClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Filled.Person, contentDescription = "Profile")
                Spacer(modifier = Modifier.width(8.dp))
                Text(stringResource(R.string.profile))
            }
            /*
            // Botón para exportar
            Button(
                onClick = onExportClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Filled.KeyboardArrowUp, contentDescription = "Export")
                Spacer(modifier = Modifier.width(8.dp))
                Text(stringResource(R.string.export))
            }

            // Botón para importar
            Button(
                onClick = onImportClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Filled.KeyboardArrowDown, contentDescription = "Import")
                Spacer(modifier = Modifier.width(8.dp))
                Text(stringResource(R.string.import_label))
            }*/

            // Botón para cerrar sesión
            Button(
                onClick = {
                    // Llama a la función de logout
                    onLogoutClick()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error)
            ) {
                Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = "Logout")
                Spacer(modifier = Modifier.width(8.dp))
                Text(stringResource(R.string.logout))
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MenuScreenPreview() {
    MeMoTheme {
        MenuScreen(
            onProfileClick = { },
            onExportClick = { },
            onImportClick = { },
            onLogoutClick = { }
        )
    }
}
