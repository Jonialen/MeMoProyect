package com.epicapp.memo.navegation

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.epicapp.memo.data.network.MemoryDO
import com.epicapp.memo.data.network.repository.MemoryRepository
import com.epicapp.memo.ui.allmemories.repository.AllMemoriesRepository
import com.epicapp.memo.ui.allmemories.view.AllMemoriesScreen
import com.epicapp.memo.ui.allmemories.viewmodel.AllMemoriesViewModel
import com.epicapp.memo.ui.allmemories.viewmodel.AllMemoriesViewModelFactory

import com.epicapp.memo.ui.detailmemories.view.DetailMemoriesScreen
import com.epicapp.memo.ui.menu.view.MenuScreen
import com.epicapp.memo.ui.theme.MeMoTheme

fun NavGraphBuilder.allMemoriesNavGraph(
    navController: NavHostController,
    memoryRepository: MemoryRepository
) {
    composable("allMemories") {
        val allMemoriesRepository = AllMemoriesRepository(memoryRepository)
        val viewModel: AllMemoriesViewModel = viewModel(
            factory = AllMemoriesViewModelFactory(allMemoriesRepository)
        )

        AllMemoriesScreen(
            onMemoryClick = { memory ->
                if (memory.id.isNotBlank()) {
                    navController.navigate("memoryView/${memory.id}")
                } else {
                    Log.e("NAVIGATION_ERROR", "ID de memoria vacío o inválido")
                }
            },
            onAddMemoryClick = { navController.navigate("editMemory") },
            onHeartClick = { navController.navigate("detailMemories") },
            onProfileClick = { navController.navigate("menu") },
            viewModel = viewModel
        )
    }

    composable("detailMemories") {
        val allMemoriesRepository = AllMemoriesRepository(memoryRepository)
        val viewModel: AllMemoriesViewModel = viewModel(
            factory = AllMemoriesViewModelFactory(allMemoriesRepository)
        )

        // Recarga las memorias cada vez que regreses
        LaunchedEffect(Unit) {
            viewModel.loadMemories()
        }

        DetailMemoriesScreen(
            allMemories = viewModel.allMemories.collectAsState().value,
            onMemoryClick = { memory ->
                if (memory.id.isNotBlank()) {
                    navController.navigate("memoryView/${memory.id}")
                } else {
                    Log.e("NAVIGATION_ERROR", "ID de memoria vacío")
                }
            },
            onAddMemoryClick = { navController.navigate("editMemory") },
            onHeartClick = {
                val popped = navController.popBackStack("allMemories", inclusive = false)
                if (!popped) {
                    Log.e("NAVIGATION_ERROR", "No se pudo regresar a 'allMemories'")
                }
            },
            onProfileClick = { navController.navigate("menu") }
        )
    }

    composable("menu") {
        MenuScreen(
            onProfileClick = { /* Implementa navegación al perfil si es necesario */ },
            onExportClick = { /* Lógica para exportar */ },
            onImportClick = { /* Lógica para importar */ },
            onLogoutClick = { navController.navigate("login") }
        )
    }
}
