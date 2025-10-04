package com.raphaelperrin.vocaboost_frontend.data.remote

import retrofit2.http.Body
import retrofit2.http.POST
import com.google.gson.annotations.SerializedName

data class BackendUserResponse(
    val id: String,
    val username: String,
    val email: String
)

data class GoogleTokenDto(
    @SerializedName("id_token")
    val idToken: String
)

interface AuthApiService {
    @POST("api/oauth2/google")
    suspend fun loginWithGoogle(@Body tokenDto: GoogleTokenDto): BackendUserResponse
}