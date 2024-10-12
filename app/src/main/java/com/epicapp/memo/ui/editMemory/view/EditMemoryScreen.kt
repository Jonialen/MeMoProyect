package com.epicapp.memo.ui.editMemory.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
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
fun MemoryEditScreen(
    memory: Memory,
    onConfirmClick: () -> Unit = {},
    onCancelClick: () -> Unit = {}
) {
    Column {
        Box(modifier = Modifier.fillMaxSize()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
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
                        Image(
                            painter = rememberAsyncImagePainter(memory.imageUrl),
                            contentDescription = "Memory Image",
                            modifier = Modifier
                                .size(120.dp)
                                .padding(end = 16.dp),
                            contentScale = ContentScale.Crop
                        )

                        Column(modifier = Modifier.weight(1f)) {
                            OutlinedTextField(
                                value = memory.date,
                                onValueChange = { /* Update date */ },
                                label = { Text("Date") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 4.dp)
                            )

                            OutlinedTextField(
                                value = memory.description,
                                onValueChange = { /* Update description */ },
                                label = { Text("Description") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 8.dp)
                            )

                            OutlinedTextField(
                                value = memory.songTitle,
                                onValueChange = { /* Update song title */ },
                                label = { Text("Song Title") },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }
        }
        Row {
            IconButton(onClick = onConfirmClick) {
                Icon(Icons.Filled.Check, contentDescription = "Confirm Edit")
            }
            IconButton(onClick = onCancelClick) {
                Icon(Icons.Filled.Close, contentDescription = "Cancel Edit")
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun MemoryScreenPreview() {
    MeMoTheme {
        val memory = Memory("1", "Memory 1", "This is a longer description for the first memory to show how the text wraps and fills the space next to the image.", "https://via.placeholder.com/150", "Song 1", "2023-01-01")

        Column {
            MemoryEditScreen(memory = memory)
        }
    }
}