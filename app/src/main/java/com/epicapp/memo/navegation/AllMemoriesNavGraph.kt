package com.epicapp.memo.navegation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.epicapp.memo.ui.allmemories.view.AllMemoriesScreen
import com.epicapp.memo.ui.allmemories.view.Memory

fun NavGraphBuilder.allMemoriesNavGraph(navController: NavHostController) {
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
        Memory("9", "Memory 3", "Description 3", "https://via.placeholder.com/150", "Song 3", "2023-01-03"),
        Memory("10", "Memory 4", "Description 4", "https://via.placeholder.com/150", "Song 4", "2023-01-04"),
        Memory("11", "Memory 5", "Description 5", "https://via.placeholder.com/150", "Song 5", "2023-01-05"),
        Memory("12", "Memory 6", "Description 6", "https://via.placeholder.com/150", "Song 6", "2023-01-06"),
    )

    composable("allMemories") {
        AllMemoriesScreen(
            favoriteMemories = favoriteMemories,
            allMemories = allMemories,
            onMemoryClick = { memory -> navController.navigate("memoryView/${memory.id}") },
            onAddMemoryClick = { navController.navigate("menu") },
            onDotClick = { navController.navigate("detailMemories") } // Lógica de navegación para el punto negro
        )
    }
}