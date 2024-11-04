package com.epicapp.memo.navegation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.epicapp.memo.ui.menu.view.MenuScreen

fun NavGraphBuilder.menuNavGraph(navController: NavHostController) {
    composable("menu") {
        MenuScreen(
            onProfileClick = { navController.navigate("profile") },
            onExportClick = { /* Lógica para exportar */ },
            onImportClick = { /* Lógica para importar */ },
            onLogoutClick = { navController.navigate("login") }
        )
    }
}
