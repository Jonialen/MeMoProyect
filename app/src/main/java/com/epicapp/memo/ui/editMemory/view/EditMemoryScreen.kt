package com.epicapp.memo.ui.editMemory.view

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.epicapp.memo.data.network.MemoryDO
import com.epicapp.memo.data.network.SongDO
import com.epicapp.memo.ui.editMemory.viewmodel.MemoryEditViewModel
import com.epicapp.memo.ui.theme.MeMoTheme
import com.epicapp.memo.utils.network.ImgBBUploader
import kotlinx.coroutines.launch

// Lista de canciones para el menú desplegable
val allSongs = listOf(
    SongDO("1", "Song 1", "Artist A"),
    SongDO("2", "Song 2", "Artist B"),
    SongDO("3", "Song 3", "Artist C"),
    SongDO("4", "Song 4", "Artist D"),
    SongDO("5", "Song 5", "Artist E")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoryEditScreen(
    memory: MemoryDO,
    viewModel: MemoryEditViewModel,
    onConfirmClick: (MemoryDO) -> Unit = {},
    onCancelClick: () -> Unit = {},
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    // Estados para los campos de entrada
    var title by remember { mutableStateOf(memory.title) }
    var date by remember { mutableStateOf(memory.date) }
    var description by remember { mutableStateOf(memory.description) }
    var searchQuery by remember { mutableStateOf(memory.songTitle) }
    var imageUrl by remember { mutableStateOf(memory.imageUrl) }
    var isLoading by remember { mutableStateOf(false) }
    var uploadError by remember { mutableStateOf<String?>(null) }
    var isDropdownExpanded by remember { mutableStateOf(false) }

    val filteredSongs = allSongs.filter {
        it.title.contains(searchQuery, ignoreCase = true) ||
                it.artist.contains(searchQuery, ignoreCase = true)
    }

    // Lanzador para seleccionar imágenes desde la galería
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            isLoading = true
            scope.launch {
                ImgBBUploader.uploadImage(
                    context = context,
                    imageUri = it,
                    onSuccess = { uploadedUrl ->
                        imageUrl = uploadedUrl
                        isLoading = false
                    },
                    onError = { errorMessage ->
                        uploadError = errorMessage
                        isLoading = false
                    }
                )
            }
        }
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
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Campo para el título
                    OutlinedTextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text("Title") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    )

                    // Campo para la descripción
                    OutlinedTextField(
                        value = description,
                        onValueChange = { newDescription -> description = newDescription },
                        label = { Text("Description") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        minLines = 3
                    )

                    // Selector de imágenes
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(bottom = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Log.d("MEMO_DEBUG", imageUrl)
                        if (imageUrl.isNotEmpty()) {
                            Image(
                                painter = rememberAsyncImagePainter(imageUrl),
                                contentDescription = "Memory Image",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            Text(
                                text = "No image selected",
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }

                        if (isLoading) {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        }
                    }

                    if (uploadError != null) {
                        Text(
                            text = uploadError ?: "",
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }
                    Button(
                        onClick = { galleryLauncher.launch("image/*") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Text("Select Image")
                    }
                    // Campo para la fecha
                    OutlinedTextField(
                        value = date,
                        onValueChange = { newDate -> date = newDate },
                        label = { Text("Date") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    )

                    // Campo para seleccionar una canción
                    ExposedDropdownMenuBox(
                        expanded = isDropdownExpanded,
                        onExpandedChange = { isDropdownExpanded = it }
                    ) {
                        OutlinedTextField(
                            value = searchQuery,
                            onValueChange = { newQuery ->
                                searchQuery = newQuery
                                isDropdownExpanded = true
                            },
                            label = { Text("Song Title") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor(),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
                        )

                        ExposedDropdownMenu(
                            expanded = isDropdownExpanded,
                            onDismissRequest = { isDropdownExpanded = false }
                        ) {
                            filteredSongs.forEach { song ->
                                DropdownMenuItem(
                                    text = { Text("${song.title} - ${song.artist}") },
                                    onClick = {
                                        searchQuery = song.title
                                        isDropdownExpanded = false
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        // Botones de acción
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

            Button(
                onClick = {
                    val updatedMemory = memory.copy(
                        title = title,
                        date = date,
                        description = description,
                        songTitle = searchQuery,
                        imageUrl = imageUrl
                    )
                    viewModel.saveMemory(updatedMemory) // Guardar la memoria usando el ViewModel
                    onCancelClick() // Regresar al cerrar
                }
            ) {
                Icon(Icons.Filled.Check, contentDescription = "Confirm Edit")
                Spacer(Modifier.width(8.dp))
                Text("Confirm")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MemoryEditScreenPreview() {
    MeMoTheme {
        // Lista mutable de memorias ficticias
        val memoryList = mutableListOf(
            MemoryDO(
                id = "1",
                title = "Memory 1",
                description = "This is a longer description for the first memory to show how the text wraps and fills the space next to the image.",
                imageUrl = "https://via.placeholder.com/150",
                songTitle = "Song 1",
                date = "2023-01-01"
            ),
            MemoryDO(
                id = "2",
                title = "Memory 2",
                description = "Another memory for testing the editing functionality.",
                imageUrl = "https://via.placeholder.com/150",
                songTitle = "Song 2",
                date = "2023-01-02"
            )
        )

        // Inicialización del repositorio con la lista ficticia
        val repository = com.epicapp.memo.ui.editMemory.repository.MemoryEditRepository(memoryList)

        // Inicialización del ViewModel con el repositorio
        val viewModel = MemoryEditViewModel(repository)

        // Pantalla de edición utilizando el ViewModel
        MemoryEditScreen(
            memory = memoryList[0], // Memoria inicial para editar
            viewModel = viewModel,
            onCancelClick = { /* Acción simulada al cancelar */ }
        )
    }
}
