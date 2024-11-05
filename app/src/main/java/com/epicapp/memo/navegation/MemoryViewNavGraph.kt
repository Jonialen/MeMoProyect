package com.epicapp.memo.navegation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.epicapp.memo.ui.memoryView.view.MemoryViewScreen
import com.epicapp.memo.ui.allmemories.view.Memory

fun NavGraphBuilder.memoryViewNavGraph(navController: NavHostController) {
    composable("memoryView/{memoryId}") { backStackEntry ->
        val memoryId = backStackEntry.arguments?.getString("memoryId")
        MemoryViewScreen(
            memory = Memory(
                id = memoryId ?: "",
                title = "Título de ejemplo",
                description = "Descripción de ejemplo",
                imageUrl = "https://via.placeholder.com/150",
                songTitle = "Canción de ejemplo",
                date = "Fecha de ejemplo"
            ),
            onEditClick = { navController.navigate("editMemory/$memoryId") } // Navegación al hacer clic en el ícono del lápiz
        )
    }
}
