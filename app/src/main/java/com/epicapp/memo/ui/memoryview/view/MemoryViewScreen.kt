package com.epicapp.memo.ui.memoryview.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.twotone.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import androidx.compose.ui.tooling.preview.Preview
import com.epicapp.memo.data.network.MemoryDO
import com.epicapp.memo.ui.theme.MeMoTheme

@Composable
fun MemoryViewScreen(
    memory: MemoryDO,
    onEditClick: () -> Unit,
    onDeleteClick: (MemoryDO) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                // Título de la memoria
                Text(
                    text = memory.title.ifBlank { "Sin título" },
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Imagen de la memoria
                Row(modifier = Modifier.fillMaxWidth()) {
                    Image(
                        painter = rememberAsyncImagePainter(memory.imageUrl),
                        contentDescription = "Memory Image",
                        modifier = Modifier
                            .size(120.dp)
                            .padding(end = 16.dp),
                        contentScale = ContentScale.Crop
                    )

                    Column(modifier = Modifier.weight(1f)) {
                        // Fecha de la memoria
                        Text(
                            text = memory.date.ifBlank { "Fecha desconocida" },
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )

                        // Descripción de la memoria
                        Text(
                            text = memory.description.ifBlank { "Descripción no disponible" },
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        // Botón para reproducir la canción
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

        // Botón flotante para editar la memoria
        FloatingActionButton(
            onClick = onEditClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(Icons.Filled.Edit, contentDescription = "Edit Memory")
        }

        // Botón flotante para eliminar la memoria
        FloatingActionButton(
            onClick = { onDeleteClick(memory) },
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
        val memory = MemoryDO("1", "Memory 1", "Esta es una descripción más larga para la primera memoria para mostrar cómo se ajusta el texto.", "https://via.placeholder.com/150", "Song 1", "2023-01-01")

        MemoryViewScreen(
            memory = memory,
            onEditClick = {},
            onDeleteClick = {} // Función de prueba para eliminar
        )
    }
}
