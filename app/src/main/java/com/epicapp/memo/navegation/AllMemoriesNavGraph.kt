package com.epicapp.memo.navegation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.epicapp.memo.ui.allmemories.view.AllMemoriesScreen
import com.epicapp.memo.ui.allmemories.view.Memory
import com.epicapp.memo.ui.detailmemories.view.DetailMemoriesScreen
import com.epicapp.memo.ui.menu.view.MenuScreen
import com.epicapp.memo.ui.theme.MeMoTheme

fun NavGraphBuilder.allMemoriesNavGraph(navController: NavHostController) {
    val favoriteMemories = listOf(
        Memory(
            "1",
            "Favorite Memory 1",
            "Description 1",
            "https://e00-elmundo.uecdn.es/assets/multimedia/imagenes/2022/08/12/16603249975433.jpg",
            "Song 1",
            "2023-01-01"
        ),
        Memory(
            "2",
            "Favorite Memory 2",
            "Description 2",
            "https://e00-elmundo.uecdn.es/assets/multimedia/imagenes/2022/08/12/16603249975433.jpg",
            "Song 2",
            "2023-01-02"
        ),
        Memory(
            "3",
            "Favorite Memory 3",
            "Description 3",
            "https://e00-elmundo.uecdn.es/assets/multimedia/imagenes/2022/08/12/16603249975433.jpg",
            "Song 3",
            "2023-01-03"
        ),
        Memory(
            "4",
            "Favorite Memory 4",
            "Description 4",
            "https://e00-elmundo.uecdn.es/assets/multimedia/imagenes/2022/08/12/16603249975433.jpg",
            "Song 4",
            "2023-01-04"
        ),
        Memory(
            "5",
            "Favorite Memory 5",
            "Description 5",
            "https://e00-elmundo.uecdn.es/assets/multimedia/imagenes/2022/08/12/16603249975433.jpg",
            "Song 5",
            "2023-01-05"
        ),
        Memory(
            "6",
            "Favorite Memory 6",
            "Description 6",
            "https://e00-elmundo.uecdn.es/assets/multimedia/imagenes/2022/08/12/16603249975433.jpg",
            "Song 6",
            "2023-01-06"
        ),
    )

    val allMemories = listOf(
        Memory(
            "7",
            "Memory 1",
            "Description 1",
            "https://e00-elmundo.uecdn.es/assets/multimedia/imagenes/2022/08/12/16603249975433.jpg",
            "Song 1",
            "2023-01-01"
        ),
        Memory(
            "8",
            "Memory 2",
            "Description 2",
            "https://e00-elmundo.uecdn.es/assets/multimedia/imagenes/2022/08/12/16603249975433.jpg",
            "Song 2",
            "2023-01-02"
        ),
        Memory(
            "9",
            "Memory 3",
            "Description 3",
            "https://e00-elmundo.uecdn.es/assets/multimedia/imagenes/2022/08/12/16603249975433.jpg",
            "Song 3",
            "2023-01-03"
        ),
        Memory(
            "10",
            "Memory 4",
            "Description 4",
            "https://e00-elmundo.uecdn.es/assets/multimedia/imagenes/2022/08/12/16603249975433.jpg",
            "Song 4",
            "2023-01-04"
        ),
        Memory(
            "11",
            "Memory 5",
            "Description 5",
            "https://e00-elmundo.uecdn.es/assets/multimedia/imagenes/2022/08/12/16603249975433.jpg",
            "Song 5",
            "2023-01-05"
        ),
        Memory(
            "12",
            "Memory 6",
            "Description 6",
            "https://e00-elmundo.uecdn.es/assets/multimedia/imagenes/2022/08/12/16603249975433.jpg",
            "Song 6",
            "2023-01-06"
        ),
    )


    composable("allMemories") {
        MeMoTheme {
            AllMemoriesScreen(
                favoriteMemories = favoriteMemories,
                allMemories = allMemories,
                onMemoryClick = { memory -> navController.navigate("memoryView/${memory.id}") },
                onAddMemoryClick = { navController.navigate("editMemory") },
                onDotClick = { navController.navigate("detailMemories") },
                onHeartClick = { navController.navigate("detailMemories") },
                onProfileClick = { navController.navigate("menu") }
            )
        }
    }

    composable("detailMemories") {
        MeMoTheme {
            DetailMemoriesScreen(
                allMemories = allMemories,
                onMemoryClick = { memory -> navController.navigate("memoryView/${memory.id}") },
                onAddMemoryClick = { navController.navigate("editMemory") }
            )
        }
    }

    composable("menu") {
        MeMoTheme {
            MenuScreen(
                onProfileClick = { /* Implementa navegación al perfil si es necesario */ },
                onExportClick = { /* Lógica para exportar */ },
                onImportClick = { /* Lógica para importar */ },
                onLogoutClick = { navController.navigate("login") }
            )
        }
    }
}