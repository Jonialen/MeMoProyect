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
            val errorMessage = when (e.localizedMessage) {
                "The email address is badly formatted." -> "El correo electrónico no tiene un formato válido."
                "There is no user record corresponding to this identifier." -> "No existe una cuenta con este correo."
                "The password is invalid or the user does not have a password." -> "La contraseña es incorrecta."
                else -> "Error desconocido: ${e.localizedMessage}"
            }
            Result.failure(Exception(errorMessage))
        }
    }

    // Registrar un nuevo usuario
    suspend fun register(email: String, password: String): Result<String> {
        return try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            Result.success("User registered successfully")
        } catch (e: Exception) {
            val errorMessage = when (e.localizedMessage) {
                "The email address is already in use by another account." -> "Este correo ya está registrado."
                "The email address is badly formatted." -> "El correo no tiene un formato válido."
                else -> "Error desconocido: ${e.localizedMessage}"
            }
            Result.failure(Exception(errorMessage))
        }
    }

    // Obtener usuario actual
    fun getCurrentUserEmail(): String? {
        return firebaseAuth.currentUser?.email
    }

    // Cerrar sesión
    fun logout() {
        FirebaseAuth.getInstance().signOut()
    }

    fun isLoggedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }

}
