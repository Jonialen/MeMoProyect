package com.epicapp.memo.ui.memoryView.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.twotone.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import androidx.compose.ui.tooling.preview.Preview
import com.epicapp.memo.ui.allmemories.view.Memory
import com.epicapp.memo.ui.theme.MeMoTheme

@Composable
fun MemoryViewScreen(
    memory: Memory,
    onEditClick: () -> Unit = {}
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
                    text = memory.title,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

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

        FloatingActionButton(
            onClick = onEditClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(Icons.Filled.Edit, contentDescription = "Edit Memory")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MemoryScreensPreview() {
    MeMoTheme {
        val memory = Memory("1", "Memory 1", "This is a longer description for the first memory to show how the text wraps and fills the space next to the image.", "https://via.placeholder.com/150", "Song 1", "2023-01-01")

        MemoryViewScreen(memory = memory)
    }
}