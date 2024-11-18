package com.epicapp.memo.navegation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.epicapp.memo.ui.profile.view.ProfileScreen
import com.epicapp.memo.ui.theme.MeMoTheme

fun NavGraphBuilder.profileNavGraph(navController: NavHostController) {
    composable("profile") {
            ProfileScreen()
        }

}