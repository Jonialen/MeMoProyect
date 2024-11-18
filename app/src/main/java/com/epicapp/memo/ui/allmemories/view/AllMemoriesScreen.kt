package com.epicapp.memo.ui.allmemories.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.epicapp.memo.R
import com.epicapp.memo.navegacion.view.CustomNavBar
import com.epicapp.memo.ui.theme.MeMoTheme
import com.epicapp.memo.data.network.MemoryDO
import com.epicapp.memo.data.network.SongDO
import com.epicapp.memo.ui.allmemories.repository.AllMemoriesRepository
import com.epicapp.memo.ui.allmemories.viewmodel.AllMemoriesViewModel


@Composable
fun MemoryCard(memory: MemoryDO, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .clickable(onClick = onClick)
            .padding(8.dp)
    ) {
        Column {
            Image(
                painter = rememberAsyncImagePainter(
                    model = memory.imageUrl,
                    onError = { error ->
                        Log.e("ImageError", "Error loading image: ${error.result.throwable?.message ?: "Unknown error"}")
                    },
                    placeholder = painterResource(R.drawable.ic_launcher_background),
                    error = painterResource(R.drawable.ic_launcher_foreground)
                ),
                contentDescription = "Memory Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentScale = ContentScale.Crop
            )
            Text(text = memory.title, style = MaterialTheme.typography.titleSmall)
            Text(text = memory.date, style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun MemoriesRow(memories: List<MemoryDO>, onMemoryClick: (MemoryDO) -> Unit) {
    LazyRow {
        items(memories) { memory ->
            MemoryCard(memory = memory, onClick = { onMemoryClick(memory) })
        }
    }
}

@Composable
fun AllMemoriesScreen(
    onMemoryClick: (MemoryDO) -> Unit,
    onAddMemoryClick: () -> Unit,
    onDotClick: () -> Unit,
    onHeartClick: () -> Unit,
    onProfileClick: () -> Unit,
    viewModel: AllMemoriesViewModel
) {
    LaunchedEffect(key1 = "generateRandom") {
        viewModel.generateRandomMemories()
    }

    val allMemories = remember { viewModel.allMemories }
    val randomMemories = remember { viewModel.randomMemories }

    val groupedMemories = allMemories.value
        .sortedByDescending { memory ->
            try {
                java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).parse(memory.date)
            } catch (e: Exception) {
                null
            }
        }
        .groupBy { it.date }

    Scaffold(
        topBar = {
            CustomNavBar(
                onHeartClick = onHeartClick,
                onProfileClick = onProfileClick
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddMemoryClick,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add Memory")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            // Sección de memorias recomendadas
            item {
                Text(
                    text = "Memorias que te pueden gustar",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }
            item {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier.height(300.dp),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(randomMemories.value.take(6), key = { it.id }) { memory ->
                        MemoryCard(memory = memory, onClick = { onMemoryClick(memory) })
                    }
                }
            }

            // Título de "Todas las Memorias"
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Todas las Memorias",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }

            // Sección de todas las memorias agrupadas por fecha
            groupedMemories.forEach { (date, memories) ->
                item {
                    Text(
                        text = "Fecha: $date",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
                memories.chunked(3).forEach { chunk ->
                    item {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(3),
                            modifier = Modifier.height(150.dp) // Ajusta la altura según lo necesario
                        ) {
                            items(chunk, key = { it.id }) { memory ->
                                MemoryCard(memory = memory, onClick = { onMemoryClick(memory) })
                            }
                        }
                    }
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun AllMemoriesScreenPreview() {
    MeMoTheme {
        val favoriteMemories = mutableListOf(
            MemoryDO("1", "Favorite Memory 1", "Description 1", "https://via.placeholder.com/150", SongDO("12", "Song G", "Artist Z"), "2023-01-01"),
            MemoryDO("2", "Favorite Memory 2", "Description 2", "https://via.placeholder.com/150", SongDO("11", "Song H", "Artist Y"), "2023-01-02")
        )

        val allMemories = mutableListOf(
            MemoryDO("7", "Memory 1", "Description 1", "https://via.placeholder.com/150", SongDO("12", "Song G", "Artist Z"), "2023-01-01"),
            MemoryDO("8", "Memory 2", "Description 2", "https://via.placeholder.com/150", SongDO("11", "Song H", "Artist Y"), "2023-01-02")
        )

        AllMemoriesScreen(
            onMemoryClick = { /* Simula navegación */ },
            onAddMemoryClick = { /* Simula acción */ },
            onDotClick = { /* Simula acción */ },
            onHeartClick = { /* Simula acción */ },
            onProfileClick = { /* Simula acción */ },
            viewModel = AllMemoriesViewModel(AllMemoriesRepository(allMemories.toMutableList())) // Datos ficticios para vista previa
        )
    }
}

