package com.raphaelperrin.vocaboost_frontend.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow

// Followed logic from https://github.com/android/snippets/blob/main/compose/snippets/src/main/java/com/example/compose/snippets/components/Navigation.kt
enum class Destination (
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    LOGIN("login", "Login", Icons.Filled.AccountBox),
    HOME("home", "Home", Icons.Filled.Home),
    PRACTICE("practice", "Practice", Icons.Filled.PlayArrow),
    STATISTICS("statistics", "Statistics", Icons.Filled.BarChart);

    companion object {
        val bottomBarDestinations = listOf(HOME, PRACTICE, STATISTICS)
    }
}