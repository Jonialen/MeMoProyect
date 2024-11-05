package com.epicapp.memo.navegation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.epicapp.memo.ui.profile.view.ProfileScreen

fun NavGraphBuilder.profileNavGraph(navController: NavHostController) {
    composable("profile") {
        ProfileScreen()
    }
}
