package com.epicapp.memo.navegation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.epicapp.memo.ui.profile.view.ProfileScreen
import com.epicapp.memo.ui.profile.viewmodel.ProfileViewModel
import com.epicapp.memo.ui.profile.viewmodel.ProfileViewModelFactory
import com.epicapp.memo.data.network.repository.AuthRepository
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider

fun NavGraphBuilder.profileNavGraph(
    navController: NavHostController,
    authRepository: AuthRepository
) {
    composable("profile") {
        val activity = navController.context as ComponentActivity
        val viewModelFactory = ProfileViewModelFactory(authRepository)
        val viewModel = ViewModelProvider(activity, viewModelFactory).get(ProfileViewModel::class.java)
        ProfileScreen(viewModel)
    }
}
