package com.epicapp.memo.navegation

import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.epicapp.memo.data.network.MemoryDO
import com.epicapp.memo.data.network.SongDO
import com.epicapp.memo.data.network.repository.MemoryRepository
import com.epicapp.memo.ui.editMemory.repository.MemoryEditRepository
import com.epicapp.memo.ui.editMemory.view.MemoryEditScreen
import com.epicapp.memo.ui.editMemory.viewmodel.MemoryEditViewModel
import com.epicapp.memo.ui.editMemory.viewmodel.MemoryEditViewModelFactory
import com.epicapp.memo.ui.theme.MeMoTheme

fun NavGraphBuilder.editMemoryNavGraph(
    navController: NavHostController,
    memoryRepository: MemoryRepository
) {
    val editRepository = MemoryEditRepository(memoryRepository)

    composable("editMemory/{memoryId}") { backStackEntry ->
        val memoryId = backStackEntry.arguments?.getString("memoryId")

        val viewModel: MemoryEditViewModel = viewModel(
            factory = MemoryEditViewModelFactory(editRepository)
        )

        if (memoryId != null) {
            MemoryEditScreen(
                memoryId = memoryId,
                viewModel = viewModel,
                onConfirmClick = { updatedMemory ->
                    viewModel.saveMemory(updatedMemory)
                    val popped = navController.popBackStack("allMemories", inclusive = false)
                    if (!popped) {
                        Log.e("NAVIGATION_ERROR", "No se pudo regresar a 'allMemories'")
                        navController.navigate("allMemories")
                    }
                },
                onCancelClick = {
                    val popped = navController.popBackStack("allMemories", inclusive = false)
                    if (!popped) {
                        Log.e("NAVIGATION_ERROR", "No se pudo regresar a 'allMemories'")
                        navController.navigate("allMemories")
                    }
                }
            )
        } else {
            // Manejo alternativo si `memoryId` es nulo
            Log.d("FALLO ID EDITAR NO NUEVA", "Error: Memory not found")
            navController.popBackStack("allMemories", inclusive = false)
        }
    }

    composable("editMemory") {
        val viewModel: MemoryEditViewModel = viewModel(
            factory = MemoryEditViewModelFactory(editRepository)
        )

        MemoryEditScreen(
            memoryId = null,
            viewModel = viewModel,
            onConfirmClick = { newMemory ->
                viewModel.saveMemory(newMemory)
                val popped = navController.popBackStack("allMemories", inclusive = false)
                if (!popped) {
                    Log.e("NAVIGATION_ERROR", "No se pudo regresar a 'allMemories'")
                    navController.navigate("allMemories")
                }
            },
            onCancelClick = {
                val popped = navController.popBackStack("allMemories", inclusive = false)
                if (!popped) {
                    Log.e("NAVIGATION_ERROR", "No se pudo regresar a 'allMemories'")
                    navController.navigate("allMemories")
                }
            }
        )
    }
}
