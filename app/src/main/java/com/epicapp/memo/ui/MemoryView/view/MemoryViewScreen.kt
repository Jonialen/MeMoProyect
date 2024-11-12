package com.epicapp.memo.ui.memoryView.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Delete // Icono de eliminar
import androidx.compose.material.icons.twotone.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import androidx.compose.ui.tooling.preview.Preview
import com.epicapp.memo.R
import com.epicapp.memo.ui.allmemories.view.Memory
import com.epicapp.memo.ui.theme.MeMoTheme

@Composable
fun MemoryViewScreen(
    memory: Memory,
    onEditClick: () -> Unit, // Función para manejar el clic en editar
    onDeleteClick: () -> Unit // Función para manejar el clic en eliminar
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = memory.title.ifBlank { "Sin título" },
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Row(modifier = Modifier.fillMaxWidth()) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            model = memory.imageUrl,
                            placeholder = painterResource(R.drawable.ic_launcher_background),
                            error = painterResource(R.drawable.ic_launcher_foreground)
                        ),
                        contentDescription = "Memory Image",
                        modifier = Modifier
                            .size(120.dp)
                            .padding(end = 16.dp),
                        contentScale = ContentScale.Crop
                    )

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = memory.date.ifBlank { "Fecha desconocida" },
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )

                        Text(
                            text = memory.description.ifBlank { "Descripción no disponible" },
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        Button(
                            onClick = { /* Aquí puedes agregar lógica para reproducir la canción */ },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(Icons.TwoTone.Notifications, contentDescription = "Play Music")
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = memory.songTitle.ifBlank { "Canción desconocida" })
                        }
                    }
                }
            }
        }

        // Botón de editar en la parte inferior derecha
        FloatingActionButton(
            onClick = onEditClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(Icons.Filled.Edit, contentDescription = "Edit Memory")
        }

        // Botón de eliminar en la parte inferior izquierda
        FloatingActionButton(
            onClick = onDeleteClick,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            Icon(Icons.Filled.Delete, contentDescription = "Delete Memory")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MemoryScreensPreview() {
    MeMoTheme {
        val memory = Memory("1", "Memory 1", "Esta es una descripción más larga para la primera memoria para mostrar cómo se ajusta el texto.", "https://via.placeholder.com/150", "Song 1", "2023-01-01")

        MemoryViewScreen(
            memory = memory,
            onEditClick = {},
            onDeleteClick = {} // Función de prueba para eliminar
        )
    }
}
