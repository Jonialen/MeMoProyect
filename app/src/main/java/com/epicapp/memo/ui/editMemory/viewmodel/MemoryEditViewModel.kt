package com.epicapp.memo.ui.editMemory.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.epicapp.memo.data.network.MemoryDO
import com.epicapp.memo.data.network.SongDO
import com.epicapp.memo.ui.editMemory.repository.MemoryEditRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class MemoryEditViewModel(private val repository: MemoryEditRepository) : ViewModel() {

    private val _memory = MutableStateFlow<MemoryDO?>(null)
    val memory: StateFlow<MemoryDO?> get() = _memory

    fun loadMemory(memoryId: String) {
        viewModelScope.launch {
            val loadedMemory = repository.getMemoryById(memoryId)
            _memory.value = loadedMemory
        }
    }

    fun saveMemory(memory: MemoryDO) {
        viewModelScope.launch {
            repository.saveMemory(memory)
        }
    }

    fun setEmptyMemory() {
        _memory.value = MemoryDO(
            id = UUID.randomUUID().toString(), // Genera un ID único si es necesario
            title = "",
            description = "",
            imageUrl = "",
            song = SongDO("", "", ""),
            date = ""
        )
    }
}

class MemoryEditViewModelFactory(
    private val repository: MemoryEditRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MemoryEditViewModel::class.java)) {
            return MemoryEditViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
