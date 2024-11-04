package com.epicapp.memo.navegation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.epicapp.memo.ui.allmemories.view.AllMemoriesScreen
import com.epicapp.memo.ui.allmemories.view.Memory

fun NavGraphBuilder.allMemoriesNavGraph(navController: NavHostController) {
    composable("allMemories") {
        AllMemoriesScreen(
            favoriteMemories = listOf(),
            allMemories = listOf(),
            onMemoryClick = { memory -> navController.navigate("memoryView/${memory.id}") },
            onAddMemoryClick = { navController.navigate("menu") },
            onHeartClick = { navController.navigate("detailMemories") },
            onProfileClick = { navController.navigate("menu") }
        )
    }
}
