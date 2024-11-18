package com.epicapp.memo.data.network.repository

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class AuthRepository {
    private val firebaseAuth = FirebaseAuth.getInstance()

    // Iniciar sesión con correo y contraseña
    suspend fun login(email: String, password: String): Result<String> {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Result.success("Login successful")
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Registrar un nuevo usuario
    suspend fun register(email: String, password: String): Result<String> {
        return try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            Result.success("User registered successfully")
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Obtener usuario actual
    fun getCurrentUser() = firebaseAuth.currentUser

    // Cerrar sesión
    fun logout() {
        FirebaseAuth.getInstance().signOut()
    }
}
