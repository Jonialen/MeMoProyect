package com.epicapp.memo.navegation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.epicapp.memo.ui.login.view.LoginScreen
import com.epicapp.memo.ui.singin.view.SingIn
import com.epicapp.memo.ui.theme.MeMoTheme

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    composable("login") {

            LoginScreen(
                onLoginClick = { navController.navigate("allMemories") },
                onCreateAccountClick = { navController.navigate("signIn") }
            )
        }

    composable("signIn") {

            SingIn(
                onLoginClick = { navController.navigate("allMemories") },
                onCreateAccountClick = { /* Lógica para crear cuenta */ }
            )
        }

}