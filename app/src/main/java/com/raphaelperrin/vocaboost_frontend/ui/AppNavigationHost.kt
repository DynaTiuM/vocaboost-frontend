package com.raphaelperrin.vocaboost_frontend.ui

import HomeScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.raphaelperrin.vocaboost_frontend.ui.navigation.Destination
import com.raphaelperrin.vocaboost_frontend.ui.screens.LoginRoute

@Composable
fun AppNavigationHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val loginRoute = Destination.LOGIN.route
    NavHost(
        navController = navController,
        startDestination = loginRoute,
        modifier = modifier
    ) {
        composable(loginRoute) {
            LoginRoute(
                onLoginSuccess = { user ->
                    navController.navigate(Destination.HOME.route) {
                        popUpTo(loginRoute) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(Destination.HOME.route) {
            HomeScreen()
        }
    }
}