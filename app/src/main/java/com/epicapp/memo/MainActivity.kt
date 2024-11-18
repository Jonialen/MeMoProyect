package com.epicapp.memo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import com.epicapp.memo.data.network.MemoryDO
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
                        "Song 1",
                        "2023-01-01"
                    ),
                    MemoryDO(
                        "2",
                        "Memory 2",
                        "Description 2",
                        "https://e00-elmundo.uecdn.es/assets/multimedia/imagenes/2022/08/12/16603249975433.jpg",
                        "Song 2",
                        "2023-01-02"
                    ),
                    MemoryDO(
                        "3",
                        "Memory 3",
                        "Description 3",
                        "https://e00-elmundo.uecdn.es/assets/multimedia/imagenes/2022/08/12/16603249975433.jpg",
                        "Song 3",
                        "2023-01-03"
                    ),
                    MemoryDO(
                        "4",
                        "Memory 4",
                        "Description 4",
                        "https://e00-elmundo.uecdn.es/assets/multimedia/imagenes/2022/08/12/16603249975433.jpg",
                        "Song 4",
                        "2023-01-04"
                    ),
                    MemoryDO(
                        "5",
                        "Memory 5",
                        "Description 5",
                        "https://e00-elmundo.uecdn.es/assets/multimedia/imagenes/2022/08/12/16603249975433.jpg",
                        "Song 5",
                        "2023-01-05"
                    ),
                    MemoryDO(
                        "6",
                        "Memory 6",
                        "Description 6",
                        "https://e00-elmundo.uecdn.es/assets/multimedia/imagenes/2022/08/12/16603249975433.jpg",
                        "Song 6",
                        "2023-01-06"
                    ),
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

    // Función para obtener 6 memorias aleatorias
    private fun getRandomSix(allMemories: SnapshotStateList<MemoryDO>): SnapshotStateList<MemoryDO> {
        val shuffled = allMemories.shuffled()
        return if (shuffled.size <= 6) shuffled.toMutableStateList() else shuffled.take(6).toMutableStateList()
    }

}
