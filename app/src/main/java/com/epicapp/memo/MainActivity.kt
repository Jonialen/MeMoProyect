package com.epicapp.memo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import com.epicapp.memo.navegation.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
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
