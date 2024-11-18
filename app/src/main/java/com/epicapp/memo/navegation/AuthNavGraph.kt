package com.epicapp.memo.navegation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.epicapp.memo.data.network.repository.AuthRepository
import com.epicapp.memo.ui.login.view.LoginScreen
import com.epicapp.memo.ui.login.viewmodel.AuthViewModel
import com.epicapp.memo.ui.login.viewmodel.AuthViewModelFactory
import com.epicapp.memo.ui.singin.view.SignInScreen
import com.epicapp.memo.ui.singin.viewmodel.SignInViewModel
import com.epicapp.memo.ui.singin.viewmodel.SignInViewModelFactory

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
) {
    composable("login") {
        val repository = AuthRepository() // O instancia compartida si es necesario
        val factory = AuthViewModelFactory(repository)
        val loginViewModel: AuthViewModel = viewModel(factory = factory)
        LoginScreen(
            viewModel = loginViewModel,
            onLoginSuccess = {
                navController.navigate("allMemories") {
                    popUpTo("login") { inclusive = true }
                }
            },
            onCreateAccountClick = {
                navController.navigate("signIn")
            }
        )
    }

    composable("signIn") {
        val signInViewModel: SignInViewModel = viewModel(factory = SignInViewModelFactory(AuthRepository()))
        SignInScreen(
            viewModel = signInViewModel,
            onSignUpSuccess = {
                navController.navigate("allMemories") {
                    popUpTo("signIn") { inclusive = true }
                }
            },
            onBackToLoginClick = { navController.popBackStack() }
        )
    }
}
