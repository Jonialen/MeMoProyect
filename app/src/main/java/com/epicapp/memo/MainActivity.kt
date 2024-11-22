package com.epicapp.memo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.epicapp.memo.data.network.MemoryDO
import com.epicapp.memo.data.network.repository.AuthRepository
import com.epicapp.memo.data.network.repository.MemoryRepository
import com.epicapp.memo.navegation.*
import com.epicapp.memo.ui.theme.MeMoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Repositorio de autenticación y memorias
        val authRepository = AuthRepository()
        val memoryRepository = MemoryRepository()

        // Verifica si el usuario ya está autenticado
        val startDestination = if (authRepository.isLoggedIn()) "allMemories" else "login"

        setContent {
            MeMoTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = startDestination) {
                    // Gráfico de navegación para autenticación
                    authNavGraph(navController)

                    // Gráfico de navegación para todas las memorias
                    allMemoriesNavGraph(navController, memoryRepository)

                    // Gráfico de navegación para la vista de una memoria específica
                    memoryViewNavGraph(navController, memoryRepository)

                    // Gráfico de navegación para editar o agregar memorias
                    editMemoryNavGraph(navController, memoryRepository)

                    // Gráfico de navegación para el menú principal
                    menuNavGraph(navController)

                    // Gráfico de navegación para el perfil del usuario (si aplica)
                    profileNavGraph(navController, authRepository)
                }
            }
        }
    }
}
