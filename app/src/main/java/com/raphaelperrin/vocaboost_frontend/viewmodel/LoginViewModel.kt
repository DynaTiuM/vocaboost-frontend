package com.raphaelperrin.vocaboost_frontend.viewmodel

import User
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raphaelperrin.vocaboost_frontend.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LoginUiState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val error: String? = null
)

sealed interface LoginEvent {
    object RequestGoogleLogin : LoginEvent
    data class GoogleLogin(val idToken: String) : LoginEvent
    object DismissError : LoginEvent
}

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when(event) {
            is LoginEvent.GoogleLogin -> {
                loginWithGoogle(event.idToken)
            }
            is LoginEvent.DismissError -> {
                _uiState.update { it.copy(error = null) }
            }
            else -> {}
        }
    }

    fun loginWithGoogle(idToken: String) {
        Log.d("LoginViewModel", "Received ID Token: $idToken")

        if (idToken.isBlank()) {
            Log.e("LoginViewModel", "ID Token is blank! Sign-in might have failed.")
            _uiState.update { it.copy(isLoading = false, error = "Failed to get Google Token.") }
            return
        }
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                val loggedInUser = userRepository.loginWithGoogle(idToken)
                _uiState.update { it.copy(isLoading = false, user = loggedInUser) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = e.localizedMessage) }
            }
        }
    }
}