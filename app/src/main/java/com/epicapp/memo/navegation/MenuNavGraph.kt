package com.epicapp.memo.navegation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import androidx.navigation.navOptions
import com.epicapp.memo.ui.menu.view.MenuScreen

fun NavGraphBuilder.menuNavGraph(navController: NavHostController) {
    composable("menu") {

        MenuScreen(
            onProfileClick = { navController.navigate("profile") }, // Navegación al perfil
            onExportClick = { /* Lógica de exportación */ },
            onImportClick = { /* Lógica de importación */ },
            onLogoutClick = {
                navController.navigate("login") {
                    popUpTo("menu") { inclusive = true } // Elimina todas las pantallas del stack
                }
            }
        )
    }
}
