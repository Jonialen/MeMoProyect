package com.epicapp.memo.ui.detailmemories.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.twotone.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import com.epicapp.memo.ui.allmemories.view.Memory

@Composable
fun DetailMemoryScreen(memories: List<Memory>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(memories) { memory ->
            MemoryItem(memory)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun MemoryItem(memory: Memory) {
    Column {
        Image(
            painter = rememberAsyncImagePainter(memory.imageUrl),
            contentDescription = "Memory Image",
            modifier = Modifier.fillMaxWidth().height(250.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = memory.title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Text(
            text = memory.date,  // Muestra la fecha de la memoria
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = memory.description,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { /* Play music */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(Icons.TwoTone.Notifications, contentDescription = "Play Music")
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = memory.songTitle)
        }

        Spacer(modifier = Modifier.height(16.dp))

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
fun DetailMemoryScreenPreview() {
    val memories = listOf(
        Memory("1", "Favorite Memory 1", "Description 1", "https://via.placeholder.com/150", "Song 1", "2023-01-01"),
        Memory("2", "Favorite Memory 2", "Description 2", "https://via.placeholder.com/150", "Song 2", "2023-01-02"),
        Memory("3", "Favorite Memory 3", "Description 3", "https://via.placeholder.com/150", "Song 3", "2023-01-03")
    )

    DetailMemoryScreen(memories = memories)
}
