package com.raphaelperrin.vocaboost_frontend.viewmodel

import User
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raphaelperrin.vocaboost_frontend.repository.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val sessionManager: SessionManager
): ViewModel() {
    val currentUser: StateFlow<User?> = this.sessionManager.currentUser
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )
}