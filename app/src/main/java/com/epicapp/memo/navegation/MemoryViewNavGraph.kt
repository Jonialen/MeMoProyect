package com.epicapp.memo.navegation

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.epicapp.memo.data.network.MemoryDO
import com.epicapp.memo.data.network.SongDO
import com.epicapp.memo.data.network.repository.MemoryRepository
import com.epicapp.memo.ui.memoryview.repository.MemoryViewRepository
import com.epicapp.memo.ui.memoryview.view.MemoryViewScreen
import com.epicapp.memo.ui.memoryview.viewmodel.MemoryViewViewModel
import com.epicapp.memo.ui.memoryview.viewmodel.MemoryViewViewModelFactory


fun NavGraphBuilder.memoryViewNavGraph(
    navController: NavHostController,
    memoryRepository: MemoryRepository
) {
    composable("memoryView/{memoryId}") { backStackEntry ->
        val memoryId = backStackEntry.arguments?.getString("memoryId") ?: ""

        val viewModel: MemoryViewViewModel = viewModel(
            factory = MemoryViewViewModelFactory(memoryRepository)
        )

        LaunchedEffect(memoryId) {
            viewModel.loadMemory(memoryId)
        }

        val memoryState = viewModel.memory.collectAsState()

        MemoryViewScreen(
            memory = memoryState.value ?: MemoryDO(
                id = "",
                title = "Memoria no encontrada",
                description = "",
                imageUrl = "",
                song = SongDO("", "", ""),
                date = ""
            ),
            onEditClick = { navController.navigate("editMemory/$memoryId") },
            onDeleteClick = { memoryToDelete ->
                if (memoryToDelete.id.isNotBlank()) {
                    viewModel.deleteMemory(memoryToDelete.id) {
                        val popped = navController.popBackStack("allMemories", inclusive = false)
                        if (!popped) {
                            navController.navigate("allMemories") // Navegar manualmente si `popBackStack` falla
                        }
                    }
                } else {
                    Log.e("DELETE_ERROR", "No se pudo eliminar la memoria: ID inválido")
                }
            }
        )
    }
}



