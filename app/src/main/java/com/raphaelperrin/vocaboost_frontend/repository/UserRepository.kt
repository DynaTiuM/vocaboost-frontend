package com.raphaelperrin.vocaboost_frontend.repository

import User
import com.raphaelperrin.vocaboost_frontend.data.remote.AuthApiService
import com.raphaelperrin.vocaboost_frontend.data.remote.GoogleTokenDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val authApiService: AuthApiService,
    private val sessionManager: SessionManager
) {
    suspend fun loginWithGoogle(idToken: String): User {
        val request = GoogleTokenDto(idToken = idToken)
        val backendResponse = authApiService.loginWithGoogle(request)

        val user = User(
            id = backendResponse.id,
            username = backendResponse.username,
            email = backendResponse.email,
            pictureUrl = backendResponse.pictureUrl
        )
        sessionManager.login(user)
        return user
    }
}
