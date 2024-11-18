package com.epicapp.memo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import com.epicapp.memo.data.network.MemoryDO
import com.epicapp.memo.data.network.SongDO
import com.epicapp.memo.navegation.*
import com.epicapp.memo.ui.theme.MeMoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Envuelve el contenido con el tema
            MeMoTheme {
                val navController = rememberNavController()
                val allMemories = mutableListOf(
                    MemoryDO(
                        "1",
                        "Memory 1",
                        "Description 1",
                        "https://e00-elmundo.uecdn.es/assets/multimedia/imagenes/2022/08/12/16603249975433.jpg",
                        SongDO("1", "Song A", "Artist X"),
                        "2023-01-06"
                    ),
                    MemoryDO(
                        "2",
                        "Memory 2",
                        "Description 2",
                        "https://e00-elmundo.uecdn.es/assets/multimedia/imagenes/2022/08/12/16603249975433.jpg",
                        SongDO("2", "Song B", "Artist Y"),
                        "2023-01-05"
                    ),
                    MemoryDO(
                        "3",
                        "Memory 3",
                        "Description 3",
                        "https://e00-elmundo.uecdn.es/assets/multimedia/imagenes/2022/08/12/16603249975433.jpg",
                        SongDO("3", "Song A", "Artist Z"),
                        "2023-01-07"
                    ),
                    MemoryDO(
                        "4",
                        "Memory 4",
                        "Description 4",
                        "https://e00-elmundo.uecdn.es/assets/multimedia/imagenes/2022/08/12/16603249975433.jpg",
                        SongDO("4", "Song C", "Artist X"),
                        "2023-01-07"
                    ),
                    MemoryDO(
                        "5",
                        "Memory 5",
                        "Description 5",
                        "https://e00-elmundo.uecdn.es/assets/multimedia/imagenes/2022/08/12/16603249975433.jpg",
                        SongDO("5", "Song D", "Artist Y"),
                        "2023-01-07"
                    ),
                    MemoryDO(
                        "6",
                        "Memory 6",
                        "Description 6",
                        "https://e00-elmundo.uecdn.es/assets/multimedia/imagenes/2022/08/12/16603249975433.jpg",
                        SongDO("6", "Song B", "Artist Z"),
                        "2023-01-07"
                    ),
                    MemoryDO(
                        "7",
                        "Memory 7",
                        "Description 7",
                        "https://e00-elmundo.uecdn.es/assets/multimedia/imagenes/2022/08/12/16603249975433.jpg",
                        SongDO("7", "Song E", "Artist X"),
                        "2023-01-07"
                    ),
                    MemoryDO(
                        "8",
                        "Memory 8",
                        "Description 8",
                        "https://e00-elmundo.uecdn.es/assets/multimedia/imagenes/2022/08/12/16603249975433.jpg",
                        SongDO("8", "Song F", "Artist Y"),
                        "2023-01-08"
                    ),
                    MemoryDO(
                        "9",
                        "Memory 9",
                        "Description 9",
                        "https://e00-elmundo.uecdn.es/assets/multimedia/imagenes/2022/08/12/16603249975433.jpg",
                        SongDO("9", "Song E", "Artist Z"),
                        "2023-01-11"
                    ),
                    MemoryDO(
                        "10",
                        "Memory 10",
                        "Description 10",
                        "https://e00-elmundo.uecdn.es/assets/multimedia/imagenes/2022/08/12/16603249975433.jpg",
                        SongDO("10", "Song G", "Artist X"),
                        "2023-01-11"
                    ),
                    MemoryDO(
                        "11",
                        "Memory 11",
                        "Description 11",
                        "https://e00-elmundo.uecdn.es/assets/multimedia/imagenes/2022/08/12/16603249975433.jpg",
                        SongDO("11", "Song H", "Artist Y"),
                        "2023-01-11"
                    ),
                    MemoryDO(
                        "12",
                        "Memory 12",
                        "Description 12",
                        "https://e00-elmundo.uecdn.es/assets/multimedia/imagenes/2022/08/12/16603249975433.jpg",
                        SongDO("12", "Song G", "Artist Z"),
                        "2023-01-11"
                    )
                )

                val favoriteMemories = allMemories.shuffled().take(6)
                NavHost(navController = navController, startDestination = "login") {
                    authNavGraph(navController)
                    allMemoriesNavGraph(navController, allMemories, favoriteMemories)
                    memoryViewNavGraph(navController, allMemories)
                    menuNavGraph(navController)
                    editMemoryNavGraph(navController, allMemories)
                    profileNavGraph(navController)
                }
            }
        }
    }

}
