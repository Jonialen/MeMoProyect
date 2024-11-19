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
import android.app.DatePickerDialog
import java.util.Calendar


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
    memoryId: String?,
    viewModel: MemoryEditViewModel,
    onConfirmClick: (MemoryDO) -> Unit = {},
    onCancelClick: () -> Unit = {},
) {

    val memory by viewModel.memory.collectAsState()

    LaunchedEffect(memoryId) {
        if (memoryId != null) {
            viewModel.loadMemory(memoryId)
        } else {
            // Si no hay memoryId, inicializa un objeto vacío
            viewModel.setEmptyMemory()
        }
    }

    memory?.let { loadedMemory ->
        val context = LocalContext.current
        val scope = rememberCoroutineScope()

        // Estados para los campos de entrada
        var title by remember { mutableStateOf(loadedMemory.title) }
        var date by remember { mutableStateOf(loadedMemory.date) }
        var description by remember { mutableStateOf(loadedMemory.description) }
        var searchQuery by remember { mutableStateOf(loadedMemory.song.title) }
        var songArtist by remember { mutableStateOf(loadedMemory.song.artist) }
        var songId by remember { mutableStateOf(loadedMemory.song.id) }
        var imageUrl by remember { mutableStateOf(loadedMemory.imageUrl) }
        var isLoading by remember { mutableStateOf(false) }
        var uploadError by remember { mutableStateOf<String?>(null) }
        var isDropdownExpanded by remember { mutableStateOf(false) }

        // DatePickerDialog
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = remember {
            DatePickerDialog(
                context,
                { _, selectedYear, selectedMonth, selectedDay ->
                    // Actualizar la fecha seleccionada
                    date = "$selectedYear-${selectedMonth + 1}-$selectedDay"
                },
                year,
                month,
                day
            )
        }

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

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Edit Memory") },
                    navigationIcon = {
                        IconButton(onClick = onCancelClick) {
                            Icon(Icons.Filled.Close, contentDescription = "Cancel Edit")
                        }
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        val updatedMemory = loadedMemory.copy(
                            title = title,
                            date = date,
                            description = description,
                            song = SongDO(songId, searchQuery, songArtist),
                            imageUrl = imageUrl
                        )
                        viewModel.saveMemory(updatedMemory)
                        onConfirmClick(updatedMemory) // Llamar al callback de confirmación
                    }
                ) {
                    Icon(Icons.Filled.Check, contentDescription = "Save Memory")
                }
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
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
                    onValueChange = { description = it },
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

                Button(
                    onClick = { galleryLauncher.launch("image/*") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text("Select Image")
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Campo para la fecha
                Text(text = "Fecha seleccionada: $date", style = MaterialTheme.typography.bodyLarge)
                Button(
                    onClick = { datePickerDialog.show() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Seleccionar Fecha")
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Campo para seleccionar una canción
                ExposedDropdownMenuBox(
                    expanded = isDropdownExpanded,
                    onExpandedChange = { isDropdownExpanded = it }
                ) {
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
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
                                    songId = song.id
                                    songArtist = song.artist
                                    isDropdownExpanded = false
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}