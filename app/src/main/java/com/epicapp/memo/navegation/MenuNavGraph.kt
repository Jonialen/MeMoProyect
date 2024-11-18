package com.epicapp.memo.navegation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import androidx.navigation.navOptions
import com.epicapp.memo.data.network.repository.AuthRepository
import com.epicapp.memo.ui.menu.view.MenuScreen

fun NavGraphBuilder.menuNavGraph(
    navController: NavHostController
) {
    composable("menu") {
        MenuScreen(
            onProfileClick = { navController.navigate("profile") },
            onExportClick = { /* Implementa la exportación */ },
            onImportClick = { /* Implementa la importación */ },
            onLogoutClick = {
                // Llama a logout y redirige al login
                val repository = AuthRepository()
                repository.logout()
                navController.navigate("login") {
                    popUpTo("menu") { inclusive = true } // Limpia el stack de navegación
                }
            }
        )
    }
}

