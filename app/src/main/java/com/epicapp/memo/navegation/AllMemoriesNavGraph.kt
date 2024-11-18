package com.epicapp.memo.navegation

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.epicapp.memo.data.network.MemoryDO
import com.epicapp.memo.ui.allmemories.repository.AllMemoriesRepository
import com.epicapp.memo.ui.allmemories.view.AllMemoriesScreen
import com.epicapp.memo.ui.allmemories.viewmodel.AllMemoriesViewModel
import com.epicapp.memo.ui.allmemories.viewmodel.AllMemoriesViewModelFactory

import com.epicapp.memo.ui.detailmemories.view.DetailMemoriesScreen
import com.epicapp.memo.ui.menu.view.MenuScreen
import com.epicapp.memo.ui.theme.MeMoTheme

fun NavGraphBuilder.allMemoriesNavGraph(
    navController: NavHostController,
    allMemories: MutableList<MemoryDO>,
    favoriteMemories: List<MemoryDO>
) {
    composable("allMemories") {
        // Inicializamos el ViewModel usando la Factory
        val viewModel: AllMemoriesViewModel = viewModel(
            factory = AllMemoriesViewModelFactory(AllMemoriesRepository(allMemories))
        )

        AllMemoriesScreen(
            onMemoryClick = { memory -> navController.navigate("memoryView/${memory.id}") },
            onAddMemoryClick = { navController.navigate("editMemory") },
            onDotClick = { navController.navigate("detailMemories") },
            onHeartClick = { navController.navigate("detailMemories") },
            onProfileClick = { navController.navigate("menu") },
            viewModel = viewModel
        )
    }

    composable("detailMemories") {
        DetailMemoriesScreen(
            allMemories = allMemories,
            onMemoryClick = { memory -> navController.navigate("memoryView/${memory.id}") },
            onAddMemoryClick = { navController.navigate("editMemory") },
            onDotClick = { navController.navigate("detailMemories") },
            onHeartClick = {
                navController.popBackStack(
                    "allMemories",
                    inclusive = false
                )
            }, // Regresar a AllMemoriesScreen
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
