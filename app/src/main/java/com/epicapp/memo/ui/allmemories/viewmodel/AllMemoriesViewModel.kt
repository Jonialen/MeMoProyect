package com.epicapp.memo.ui.allmemories.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.epicapp.memo.data.network.MemoryDO
import com.epicapp.memo.ui.allmemories.repository.AllMemoriesRepository

class AllMemoriesViewModel(private val repository: AllMemoriesRepository) : ViewModel() {
    val allMemories = mutableStateOf(repository.getAllMemories())
    val favoriteMemories = mutableStateOf(repository.getFavoriteMemories())
    val randomMemories = mutableStateOf<List<MemoryDO>>(emptyList()) // Estado para memorias aleatorias

    init {
        generateRandomMemories()
    }

    fun addMemory(memory: MemoryDO) {
        repository.addMemory(memory)
        updateMemories()
    }

    fun deleteMemory(memory: MemoryDO) {
        repository.deleteMemory(memory)
        updateMemories()
    }

    private fun updateMemories() {
        allMemories.value = repository.getAllMemories()
        favoriteMemories.value = repository.getFavoriteMemories()
        generateRandomMemories()
    }

    fun generateRandomMemories() {
        val all = repository.getAllMemories()
        randomMemories.value = if (all.size <= 6) all.shuffled() else all.shuffled().take(6)
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