package com.epicapp.memo.navegation

import android.util.Log
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.epicapp.memo.ui.memoryView.view.MemoryViewScreen
import com.epicapp.memo.ui.allmemories.view.Memory

fun NavGraphBuilder.memoryViewNavGraph(navController: NavHostController) {
    // Lista mutable de recuerdos para simulación de prueba en Logcat
    val memoriesList = mutableListOf(
        Memory("1", "Memory 1", "Descripción 1", "https://via.placeholder.com/150", "Song 1", "2023-01-01"),
        Memory("2", "Memory 2", "Descripción 2", "https://via.placeholder.com/150", "Song 2", "2023-01-02"),
        // Agrega más elementos si deseas hacer más pruebas
    )

    composable("memoryView/{memoryId}") { backStackEntry ->
        val memoryId = backStackEntry.arguments?.getString("memoryId")

        // Log para verificar el URL de la imagen antes de mostrarla en MemoryViewScreen
        val memoryImageUrl = "https://e00-elmundo.uecdn.es/assets/multimedia/imagenes/2022/08/12/16603249975433.jpg" // URL de ejemplo
        Log.d("MemoryViewNavGraph", "URL de la imagen en MemoryViewScreen: $memoryImageUrl")

        MemoryViewScreen(
            memory = Memory(
                id = memoryId ?: "",
                title = "Título de ejemplo",
                description = "Descripción de ejemplo",
                imageUrl = memoryImageUrl,
                songTitle = "Canción de ejemplo",
                date = "Fecha de ejemplo"
            ),
            onEditClick = { navController.navigate("editMemory/$memoryId") },
            onDeleteClick = {
                Log.d("MemoryViewNavGraph", "Lista antes de eliminar: $memoriesList")
                memoriesList.removeIf { it.id == memoryId } // Simulación de eliminación
                Log.d("MemoryViewNavGraph", "Lista después de eliminar: $memoriesList")
                navController.popBackStack("allMemories", inclusive = false)
            }
        )
    }
}


