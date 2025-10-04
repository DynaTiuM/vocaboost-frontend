package com.raphaelperrin.vocaboost_frontend.ui.screens

import LoginScreen
import User
import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.raphaelperrin.vocaboost_frontend.viewmodel.LoginViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import com.raphaelperrin.vocaboost_frontend.R
import com.raphaelperrin.vocaboost_frontend.viewmodel.LoginEvent

@Composable
fun LoginRoute(
    loginViewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess: (User) -> Unit
) {
    val uiState by loginViewModel.uiState.collectAsState()
    val context = LocalContext.current

    val oneTapClient: SignInClient = Identity.getSignInClient(context)

    val googleSignInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult()
    ) {
        result ->
        if (result.resultCode == Activity.RESULT_OK) {
            try {
                val credential = oneTapClient.getSignInCredentialFromIntent(result.data)
                val idToken = credential.googleIdToken

                Log.d("LoginRoute", "Google ID Token retrieved: $idToken")

                if (idToken != null) {
                    loginViewModel.onEvent(LoginEvent.GoogleLogin(idToken))
                } else {
                    Log.e("LoginRoute", "Google ID Token was null in credential.")
                }

            } catch (e: ApiException) {
                when (e.statusCode) {
                    CommonStatusCodes.CANCELED -> {
                        Log.d("LoginRoute", "One-tap sign in was cancelled.")
                    }
                    CommonStatusCodes.NETWORK_ERROR -> {
                        Log.e("LoginRoute", "One-tap sign in failed due to network error.", e)
                    }
                    else -> {
                        Log.e("LoginRoute", "One-tap sign in failed with an unexpected error.", e)
                    }
                }
            }
        } else {
            Log.w("LoginRoute", "Sign-in intent failed with resultCode: ${result.resultCode}")
        }
    }

    LaunchedEffect(uiState.user) {
        uiState.user?.let { user -> onLoginSuccess(user) }
    }

    LoginScreen(
        uiState = uiState,
        onEvent = { event ->
            when (event) {
                is LoginEvent.RequestGoogleLogin -> {
                    oneTapClient.signOut().addOnCompleteListener {
                        // Documentation: https://developers.google.com/android/reference/com/google/android/gms/auth/api/identity/SignInClient
                        val signInRequest = BeginSignInRequest.builder()
                            .setGoogleIdTokenRequestOptions(
                                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                                    .setSupported(true)
                                    .setFilterByAuthorizedAccounts(false)
                                    .setServerClientId(context.getString(R.string.web_client_id))
                                    .build()
                            )
                            .build()
                        // https://developers.google.com/identity/sign-in/android/sign-in-identity?hl=fr
                        oneTapClient.beginSignIn(signInRequest)
                            .addOnSuccessListener { result ->
                                Log.d("LoginRoute", "Begin sign-in success. Launching intent.")
                                googleSignInLauncher.launch(
                                    IntentSenderRequest.Builder(result.pendingIntent.intentSender).build()
                                )
                            }.addOnFailureListener { e ->
                                Log.e("LoginRoute", "Begin sign-in failed", e)
                            }
                    }
                }
                else -> loginViewModel.onEvent(event)
            }
        }
    )
}
