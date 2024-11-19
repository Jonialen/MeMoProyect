package com.epicapp.memo.ui.memoryview.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.epicapp.memo.data.network.MemoryDO
import com.epicapp.memo.data.network.repository.MemoryRepository
import com.epicapp.memo.ui.memoryview.repository.MemoryViewRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class MemoryViewViewModel(private val repository: MemoryViewRepository) : ViewModel() {

    private val _memory = MutableStateFlow<MemoryDO?>(null)
    val memory: StateFlow<MemoryDO?> get() = _memory

    fun loadMemory(id: String) {
        viewModelScope.launch {
            _memory.value = repository.getMemoryById(id)
        }
    }

    fun deleteMemory(memoryId: String, onComplete: () -> Unit) {
        viewModelScope.launch {
            try {
                repository.memoryRepository.deleteMemory(memoryId)
                onComplete() // Notifica que la eliminación fue exitosa
            } catch (e: Exception) {
                Log.e("VIEWMODEL", "Error al eliminar memoria: ${e.localizedMessage}")
            }
        }
    }


}


class MemoryViewViewModelFactory(private val memoryRepository: MemoryRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MemoryViewViewModel::class.java)) {
            val memoryViewRepository = MemoryViewRepository(memoryRepository)
            return MemoryViewViewModel(memoryViewRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
