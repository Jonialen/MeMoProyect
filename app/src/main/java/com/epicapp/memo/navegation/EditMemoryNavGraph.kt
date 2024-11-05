package com.epicapp.memo.navegation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.epicapp.memo.ui.editMemory.view.MemoryEditScreen
import com.epicapp.memo.ui.allmemories.view.Memory

fun NavGraphBuilder.editMemoryNavGraph(navController: NavHostController) {
    composable("editMemory/{memoryId}") { backStackEntry ->
        val memoryId = backStackEntry.arguments?.getString("memoryId")
        MemoryEditScreen(
            memory = Memory(
                id = memoryId ?: "",
                title = "",
                description = "",
                imageUrl = "",
                songTitle = "",
                date = ""
            ),
            onConfirmClick = { /* Lógica para confirmar la edición */ },
            onCancelClick = { navController.popBackStack() }
        )
    }
}
