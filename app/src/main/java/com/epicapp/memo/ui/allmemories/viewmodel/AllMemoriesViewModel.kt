package com.epicapp.memo.ui.allmemories.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.epicapp.memo.data.network.MemoryDO
import com.epicapp.memo.ui.allmemories.repository.AllMemoriesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AllMemoriesViewModel(private val repository: AllMemoriesRepository) : ViewModel() {

    private val _allMemories = MutableStateFlow<List<MemoryDO>>(emptyList())
    val allMemories: StateFlow<List<MemoryDO>> get() = _allMemories

    val randomMemories = mutableStateOf<List<MemoryDO>>(emptyList()) // Estado para memorias aleatorias

    init {
        loadMemories()
    }

    // Cargar todas las memorias
    fun loadMemories() {
        viewModelScope.launch {
            try {
                val memories = repository.getAllMemories()
                _allMemories.value = memories
                Log.d("LOAD_MEMORIES", "Memories loaded: $memories")
                generateRandomMemories(memories) // Generar memorias aleatorias después de cargar
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("LOAD_MEMORIES", "Error loading memories: ${e.localizedMessage}")
            }
        }
    }

    // Generar 6 memorias aleatorias
    private fun generateRandomMemories(memories: List<MemoryDO>) {
        randomMemories.value = if (memories.size <= 6) memories.shuffled() else memories.shuffled().take(6)
    }

}

// Factory para inyectar el repositorio desde MainActivity
class AllMemoriesViewModelFactory(
    private val repository: AllMemoriesRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AllMemoriesViewModel::class.java)) {
            return AllMemoriesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
