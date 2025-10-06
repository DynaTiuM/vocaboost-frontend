package com.raphaelperrin.vocaboost_frontend.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.raphaelperrin.vocaboost_frontend.ui.components.BottomNavigationBar
import com.raphaelperrin.vocaboost_frontend.ui.navigation.Destination

@Composable
fun VocaboostApp() {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val routesWithScaffold = Destination.bottomBarDestinations.map { it.route }

    if (currentRoute in routesWithScaffold) {
        Scaffold(
          bottomBar = {
              BottomNavigationBar(navController = navController)
          }
        ) { innerPadding ->
            AppNavigationHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
    else {
        AppNavigationHost(navController = navController)
    }
}