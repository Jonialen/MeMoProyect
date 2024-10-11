package com.epicapp.memo.ui.allmemories.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter

data class Memory(
    val id: String, // Identificador único para cada memoria
    val title: String,
    val description: String,
    val imageUrl: String,
    val songTitle: String,
    val date: String
)

@Composable
fun MemoryCard(memory: Memory, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(8.dp)
    ) {
        Column {
            Image(
                painter = rememberAsyncImagePainter(memory.imageUrl),
                contentDescription = "Memory Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )
            Text(text = memory.title, style = MaterialTheme.typography.titleMedium)
            Text(text = memory.date, style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun AllMemoriesScreen(
    favoriteMemories: List<Memory>,
    allMemories: List<Memory>,
    onMemoryClick: (Memory) -> Unit,
    onAddMemoryClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Memorias Favoritas",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(favoriteMemories, key = { it.id }) { memory ->
                MemoryCard(memory = memory, onClick = { onMemoryClick(memory) })
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Todas las Memorias",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(allMemories, key = { it.id }) { memory ->
                MemoryCard(memory = memory, onClick = { onMemoryClick(memory) })
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        FloatingActionButton(
            onClick = onAddMemoryClick,
            modifier = Modifier
                .align(Alignment.End)
                .padding(16.dp)
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Add Memory")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AllMemoriesScreenPreview() {
    val favoriteMemories = listOf(
        Memory("1", "Favorite Memory 1", "Description 1", "https://via.placeholder.com/150", "Song 1", "2023-01-01"),
        Memory("2", "Favorite Memory 2", "Description 2", "https://via.placeholder.com/150", "Song 2", "2023-01-02"),
        Memory("3", "Favorite Memory 3", "Description 3", "https://via.placeholder.com/150", "Song 3", "2023-01-03"),
        Memory("4", "Favorite Memory 4", "Description 4", "https://via.placeholder.com/150", "Song 4", "2023-01-04"),
        Memory("5", "Favorite Memory 5", "Description 5", "https://via.placeholder.com/150", "Song 5", "2023-01-05"),
        Memory("6", "Favorite Memory 6", "Description 6", "https://via.placeholder.com/150", "Song 6", "2023-01-06"),
    )

    val allMemories = listOf(
        Memory("7", "Memory 1", "Description 1", "https://via.placeholder.com/150", "Song 1", "2023-01-01"),
        Memory("8", "Memory 2", "Description 2", "https://via.placeholder.com/150", "Song 2", "2023-01-02"),
    )

    AllMemoriesScreen(
        favoriteMemories = favoriteMemories,
        allMemories = allMemories,
        onMemoryClick = {},
        onAddMemoryClick = {}
    )
}

