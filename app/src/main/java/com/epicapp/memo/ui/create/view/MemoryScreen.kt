package com.epicapp.memo.ui.create.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import androidx.compose.ui.tooling.preview.Preview
import com.epicapp.memo.ui.allmemories.view.Memory
import com.epicapp.memo.ui.detailmemories.view.DetailMemoriesScreen
import com.epicapp.memo.ui.theme.MeMoTheme

@Composable
fun MemoryScreen(memory: Memory) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = rememberAsyncImagePainter(memory.imageUrl),
                contentDescription = "Memory Image",
                modifier = Modifier
                    .size(120.dp)
                    .padding(end = 16.dp),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = memory.title,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = memory.date,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                Text(
                    text = memory.description,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Button(
                    onClick = { /* Play music */ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.TwoTone.Notifications, contentDescription = "Play Music")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = memory.songTitle)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailMemoryScreenPreview() {
    MeMoTheme {
        val memories = listOf(
            Memory("1", "Memory 1", "This is a longer description for the first memory to show how the text wraps and fills the space next to the image.", "https://via.placeholder.com/150", "Song 1", "2023-01-01"),
            Memory("2", "Favorite Memory 2", "Another description for the second memory item in the list.", "https://via.placeholder.com/150", "Song 2", "2023-01-02"),
            Memory("3", "Favorite Memory 3", "A third memory description to demonstrate the layout with multiple items.", "https://via.placeholder.com/150", "Song 3", "2023-01-03"),
            Memory("4", "Favorite Memory 4", "A third memory description to demonstrate the layout with multiple items.", "https://via.placeholder.com/150", "Song 3", "2023-01-03")
        )

        MemoryScreen(memory = memories[0])
    }
}