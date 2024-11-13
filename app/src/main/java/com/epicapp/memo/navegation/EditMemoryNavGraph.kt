package com.epicapp.memo.navegation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.epicapp.memo.ui.editMemory.view.MemoryEditScreen
import com.epicapp.memo.ui.allmemories.view.Memory
import com.epicapp.memo.ui.theme.MeMoTheme

fun NavGraphBuilder.editMemoryNavGraph(navController: NavHostController) {
    // Ruta para editar una memoria existente
    composable("editMemory/{memoryId}") { backStackEntry ->
        val memoryId = backStackEntry.arguments?.getString("memoryId")

        MemoryEditScreen(
            memory = Memory(
                id = memoryId ?: "",
                title = "Editar Título",
                description = "Editar Descripción",
                imageUrl = "https://via.placeholder.com/150",
                songTitle = "Editar Canción",
                date = "Editar Fecha"
            ),
            onConfirmClick = { /* Lógica para confirmar la edición */ },
            onCancelClick = { navController.popBackStack() } // Regresar a la pantalla anterior
        )

    }
    // Ruta para crear una nueva memoria
    composable("editMemory") {

        MemoryEditScreen(
            memory = Memory("", "", "", "", "", ""), // Memoria vacía para una nueva entrada
            onConfirmClick = { /* Lógica para guardar la nueva memoria */ },
            onCancelClick = { navController.popBackStack() } // Regresar a la pantalla anterior
        )
    }

}