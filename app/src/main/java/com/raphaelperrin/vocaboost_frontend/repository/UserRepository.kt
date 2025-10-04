package com.raphaelperrin.vocaboost_frontend.repository

import User
import com.raphaelperrin.vocaboost_frontend.data.remote.AuthApiService
import com.raphaelperrin.vocaboost_frontend.data.remote.GoogleTokenDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val authApiService: AuthApiService
) {
    suspend fun loginWithGoogle(idToken: String): User {
        val request = GoogleTokenDto(idToken = idToken)
        val backendResponse = authApiService.loginWithGoogle(request)

        return User(
            id = backendResponse.id,
            username = backendResponse.username,
            email = backendResponse.email
        )
    }
}
