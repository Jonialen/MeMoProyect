package com.epicapp.memo.navegation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.epicapp.memo.ui.menu.view.MenuScreen
import com.epicapp.memo.ui.theme.MeMoTheme

fun NavGraphBuilder.menuNavGraph(navController: NavHostController) {
    composable("menu") {
        MeMoTheme {
            MenuScreen(
                onProfileClick = { navController.navigate("profile") }, // Navegación al perfil
                onExportClick = { /* Lógica de exportación */ },
                onImportClick = { /* Lógica de importación */ },
                onLogoutClick = { navController.navigate("login") } // Navegación al inicio de sesión
            )
        }
    }
}