package com.epicapp.memo.navegation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.epicapp.memo.data.network.MemoryDO
import com.epicapp.memo.ui.memoryview.repository.MemoryViewRepository
import com.epicapp.memo.ui.memoryview.view.MemoryViewScreen
import com.epicapp.memo.ui.memoryview.viewmodel.MemoryViewViewModel
import com.epicapp.memo.ui.memoryview.viewmodel.MemoryViewViewModelFactory


fun NavGraphBuilder.memoryViewNavGraph(
    navController: NavHostController,
    allMemories: List<MemoryDO>
) {
    composable("memoryView/{memoryId}") { backStackEntry ->
        val memoryId = backStackEntry.arguments?.getString("memoryId") ?: ""

        // Configuración del ViewModel
        val viewModel: MemoryViewViewModel = viewModel(
            factory = MemoryViewViewModelFactory(MemoryViewRepository(allMemories))
        )

        val memory = viewModel.getMemory(memoryId)

        MemoryViewScreen(
            memory = memory ?: MemoryDO("", "Memoria no encontrada", "", "", "", ""),
            onEditClick = { navController.navigate("editMemory/$memoryId") },
            onDeleteClick = {
                val memoryToDelete = viewModel.getMemory(memoryId)
                memoryToDelete?.let {
                    (allMemories as MutableList).remove(it) // Elimina la memoria
                    navController.popBackStack("allMemories", inclusive = false)
                }
            }
        )
    }
}



