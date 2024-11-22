package com.epicapp.memo.ui.profile.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.epicapp.memo.data.network.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val profileRepository: AuthRepository
) : ViewModel() {
    private val _email = MutableStateFlow<String?>(null)
    val email: StateFlow<String?> get() = _email

    init {
        loadUserEmail()
    }

    private fun loadUserEmail() {
        try {
            viewModelScope.launch {
                _email.value = profileRepository.getCurrentUserEmail()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("LOAD_MAIL", "Error loading mail: ${e.localizedMessage}")
        }
    }
}

class ProfileViewModelFactory(
    private val authRepository: AuthRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(authRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}