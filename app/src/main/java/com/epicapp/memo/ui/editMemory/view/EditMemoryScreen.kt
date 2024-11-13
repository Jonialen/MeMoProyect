package com.epicapp.memo.ui.editMemory.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import androidx.compose.ui.tooling.preview.Preview
import com.epicapp.memo.ui.allmemories.view.Memory
import com.epicapp.memo.ui.theme.MeMoTheme

data class Song(
    val id: String,
    val title: String,
    val artist: String
)

@Composable
fun MemoryEditScreen(
    memory: Memory,
    onConfirmClick: () -> Unit = {},
    onCancelClick: () -> Unit = {}
) {
    // Lista ficticia de canciones y artistas
    val allSongs = listOf(
        Song("1", "Song 1", "Artist A"),
        Song("2", "Song 2", "Artist B"),
        Song("3", "Song 1", "Artist C"),
        Song("4", "Song 4", "Artist A"),
        Song("5", "Song 5", "Artist C"),
    )

    var searchQuery by remember { mutableStateOf("") }
    var isDropdownExpanded by remember { mutableStateOf(false) }

    // Filtrar canciones según el query de búsqueda
    val filteredSongs = allSongs.filter {
        it.title.contains(searchQuery, ignoreCase = true) ||
                it.artist.contains(searchQuery, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    OutlinedTextField(
                        value = memory.title,
                        onValueChange = { /* Update title */ },
                        label = { Text("Title") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    )

                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.weight(1f)) {
                            Image(
                                painter = rememberAsyncImagePainter(memory.imageUrl),
                                contentDescription = "Memory Image",
                                modifier = Modifier
                                    .size(120.dp)
                                    .padding(end = 16.dp),
                                contentScale = ContentScale.Crop
                            )
                            OutlinedTextField(
                                value = memory.date,
                                onValueChange = { /* Update date */ },
                                label = { Text("Date") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 4.dp)
                            )
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            OutlinedTextField(
                                value = memory.description,
                                onValueChange = { /* Update description */ },
                                label = { Text("Description") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 8.dp)
                            )

                            // Campo "Song Title" con búsqueda y lista desplegable
                            OutlinedTextField(
                                value = searchQuery,
                                onValueChange = { newQuery ->
                                    searchQuery = newQuery
                                    isDropdownExpanded = true // Mostrar el menú al escribir
                                },
                                label = { Text("Song Title") },
                                modifier = Modifier.fillMaxWidth(),
                                singleLine = true
                            )

                            // Mostrar resultados de búsqueda en el DropdownMenu
                            DropdownMenu(
                                expanded = isDropdownExpanded && filteredSongs.isNotEmpty(),
                                onDismissRequest = { isDropdownExpanded = false }
                            ) {
                                filteredSongs.forEach { song ->
                                    DropdownMenuItem(
                                        text = { Text("${song.title} - ${song.artist}") },
                                        onClick = {
                                            // Actualizar el campo de búsqueda con el título seleccionado
                                            searchQuery = song.title
                                            isDropdownExpanded = false // Cerrar el menú
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = onCancelClick,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) {
                Icon(Icons.Filled.Close, contentDescription = "Cancel Edit")
                Spacer(Modifier.width(8.dp))
                Text("Cancel")
            }
            Button(onClick = onConfirmClick) {
                Icon(Icons.Filled.Check, contentDescription = "Confirm Edit")
                Spacer(Modifier.width(8.dp))
                Text("Confirm")
            }
        }
    }
}

@Composable
fun SongItem(song: Song) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "Título: ${song.title}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Artista: ${song.artist}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MemoryScreenPreview() {
    MeMoTheme {
        val memory = Memory("1", "Memory 1", "This is a longer description for the first memory to show how the text wraps and fills the space next to the image.", "https://via.placeholder.com/150", "Song 1", "2023-01-01")

        MemoryEditScreen(memory = memory)
    }
}
