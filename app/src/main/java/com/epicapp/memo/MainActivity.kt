package com.epicapp.memo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.epicapp.memo.ui.login.view.LoginScreen
import com.epicapp.memo.ui.allmemories.view.AllMemoriesScreen
import com.epicapp.memo.ui.memoryView.view.MemoryViewScreen
import com.epicapp.memo.ui.menu.view.MenuScreen
import com.epicapp.memo.ui.detailmemories.view.DetailMemoriesScreen
import com.epicapp.memo.ui.editMemory.view.MemoryEditScreen
import com.epicapp.memo.ui.singin.view.SingIn
import com.epicapp.memo.ui.theme.MeMoTheme
import com.epicapp.memo.ui.allmemories.view.Memory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeMoTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "login") {
                    composable("login") {
                        LoginScreen(
                            onLoginClick = { navController.navigate("allMemories") },
                            onCreateAccountClick = { navController.navigate("signIn") }
                        )
                    }
                    composable("allMemories") {
                        AllMemoriesScreen(
                            favoriteMemories = listOf(),
                            allMemories = listOf(),
                            onMemoryClick = { memory -> navController.navigate("memoryView/${memory.id}") },
                            onAddMemoryClick = { navController.navigate("menu") },
                            onHeartClick = { navController.navigate("detailMemories") },
                            onProfileClick = { navController.navigate("menu") }
                        )
                    }

                    composable("signIn") {
                        SingIn(
                            onLoginClick = { navController.navigate("allMemories") },
                            onCreateAccountClick = { /* acción para crear cuenta */ }
                        )
                    }
                    composable("detailMemories") {
                        DetailMemoriesScreen(
                            allMemories = listOf(),
                            onAddMemoryClick = { navController.navigate("addMemory") },
                            onMemoryClick = { memory -> navController.navigate("memoryView/${memory.id}") }
                        )
                    }

                    composable("memoryView/{memoryId}") { backStackEntry ->
                        val memoryId = backStackEntry.arguments?.getString("memoryId")
                        MemoryViewScreen(
                            memory = Memory(
                                id = memoryId ?: "",
                                title = "Título de ejemplo",
                                description = "Descripción de ejemplo",
                                imageUrl = "https://via.placeholder.com/150",
                                songTitle = "Canción de ejemplo",
                                date = "Fecha de ejemplo"
                            ),
                            onEditClick = { navController.navigate("editMemory/$memoryId") } // Navegar a editMemory con el ID
                        )
                    }
                    composable("menu") {
                        MenuScreen(
                            onProfileClick = { navController.navigate("profile") },
                            onExportClick = { /* Lógica para exportar */ },
                            onImportClick = { /* Lógica para importar */ },
                            onLogoutClick = { navController.navigate("login") }
                        )
                    }
                    composable("editMemory/{memoryId}") { backStackEntry ->
                        val memoryId = backStackEntry.arguments?.getString("memoryId")
                        MemoryEditScreen(
                            memory = Memory(
                                id = memoryId ?: "",
                                title = "",
                                description = "",
                                imageUrl = "",
                                songTitle = "",
                                date = ""
                            ),
                            onConfirmClick = { /* Confirm action */ },
                            onCancelClick = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}
