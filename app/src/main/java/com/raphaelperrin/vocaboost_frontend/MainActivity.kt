package com.raphaelperrin.vocaboost_frontend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.raphaelperrin.vocaboost_frontend.ui.screens.LoginRoute
import com.raphaelperrin.vocaboost_frontend.ui.theme.VocaboostfrontendTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VocaboostfrontendTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginRoute(
                        onLoginSuccess = { user ->
                            // TODO: Handle successful login
                            println("Login successful for user: ${user.username}")
                        }
                    )
                }
            }
        }
    }
}
