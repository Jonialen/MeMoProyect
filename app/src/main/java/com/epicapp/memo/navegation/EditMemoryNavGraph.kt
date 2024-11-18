package com.epicapp.memo.navegation

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.epicapp.memo.data.network.MemoryDO
import com.epicapp.memo.ui.editMemory.repository.MemoryEditRepository
import com.epicapp.memo.ui.editMemory.view.MemoryEditScreen
import com.epicapp.memo.ui.editMemory.viewmodel.MemoryEditViewModel
import com.epicapp.memo.ui.editMemory.viewmodel.MemoryEditViewModelFactory
import com.epicapp.memo.ui.theme.MeMoTheme

fun NavGraphBuilder.editMemoryNavGraph(
    navController: NavHostController,
    allMemories: MutableList<MemoryDO>
) {
    // Editar una memoria existente
    composable("editMemory/{memoryId}") { backStackEntry ->
        val memoryId = backStackEntry.arguments?.getString("memoryId")
        val memory = allMemories.find { it.id == memoryId }

        val viewModel: MemoryEditViewModel = viewModel(
            factory = MemoryEditViewModelFactory(MemoryEditRepository(allMemories))
        )

        MemoryEditScreen(
            memory = memory ?: MemoryDO("", "", "", "", "", ""),
            viewModel = viewModel,
            onConfirmClick = { updatedMemory ->
                viewModel.saveMemory(updatedMemory)
                navController.popBackStack() // Regresar a la pantalla anterior
            },
            onCancelClick = { navController.popBackStack() }
        )
    }

    // Crear una nueva memoria
    composable("editMemory") {
        val viewModel: MemoryEditViewModel = viewModel(
            factory = MemoryEditViewModelFactory(MemoryEditRepository(allMemories))
        )

        MemoryEditScreen(
            memory = MemoryDO("", "", "", "", "", ""), // Memoria vacía para nueva entrada
            viewModel = viewModel,
            onConfirmClick = { newMemory ->
                viewModel.saveMemory(newMemory)
                navController.popBackStack() // Regresar a la pantalla anterior
            },
            onCancelClick = { navController.popBackStack() }
        )
    }
}