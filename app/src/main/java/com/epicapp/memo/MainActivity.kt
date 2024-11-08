package com.epicapp.memo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import com.epicapp.memo.navegation.*
import com.epicapp.memo.ui.theme.MeMoTheme // Importa el tema personalizado

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Envuelve el contenido con el tema
            MeMoTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "login") {
                    authNavGraph(navController)
                    allMemoriesNavGraph(navController)
                    memoryViewNavGraph(navController)
                    menuNavGraph(navController)
                    editMemoryNavGraph(navController)
                    profileNavGraph(navController) // Agregada la navegación al perfil
                }
            }
        }
    }
}
