package com.epicapp.memo.ui.detailmemories.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.twotone.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import androidx.compose.ui.tooling.preview.Preview
import com.epicapp.memo.R
import com.epicapp.memo.data.network.MemoryDO
import com.epicapp.memo.data.network.SongDO
import com.epicapp.memo.ui.theme.MeMoTheme
import com.epicapp.memo.navegacion.view.CustomNavBar  // Importación de CustomNavBar

@Composable
fun DetailMemoriesScreen(
    allMemories: List<MemoryDO>,
    onMemoryClick: (MemoryDO) -> Unit,
    onAddMemoryClick: () -> Unit,
    onHeartClick: () -> Unit,
    onProfileClick: () -> Unit
) {
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
                Icon(Icons.Default.Add, contentDescription = stringResource(R.string.add_memory))
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.my_memories),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(allMemories) { memory ->
                    MemoryItem(memory = memory, onClick = { onMemoryClick(memory) })
                }
            }
        }
    }
}

@Composable
fun MemoryItem(memory: MemoryDO, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Column {
                Image(
                    painter = rememberAsyncImagePainter(memory.imageUrl),
                    contentDescription = stringResource(R.string.memory_image),
                    modifier = Modifier
                        .size(120.dp)
                        .padding(end = 16.dp),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = memory.date,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = memory.title,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
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
                    Icon(Icons.TwoTone.Notifications, contentDescription = stringResource(R.string.play_music))
                    Spacer(modifier = Modifier.width(8.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = memory.song.title)
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
            MemoryDO("1", "Favorite Memory 1", "This is a longer description for the first memory to show how the text wraps and fills the space next to the image.", "https://via.placeholder.com/150", SongDO("12", "Song G", "Artist Z"), "2023-01-01"),
            MemoryDO("2", "Favorite Memory 2", "Another description for the second memory item in the list.", "https://via.placeholder.com/150", SongDO("12", "Song G", "Artist Z"), "2023-01-02"),
            MemoryDO("3", "Favorite Memory 3", "A third memory description to demonstrate the layout with multiple items.", "https://via.placeholder.com/150", SongDO("12", "Song G", "Artist Z"), "2023-01-03"),
            MemoryDO("4", "Favorite Memory 4", "A fourth memory description to demonstrate the layout with multiple items.", "https://via.placeholder.com/150", SongDO("12", "Song G", "Artist Z"), "2023-01-04")
        )

        DetailMemoriesScreen(
            allMemories = memories,
            onMemoryClick = {},
            onAddMemoryClick = {},
            onHeartClick = {},
            onProfileClick = {}
        )
    }
}
