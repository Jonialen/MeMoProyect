package com.epicapp.memo.ui.editMemory.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.epicapp.memo.data.network.MemoryDO
import com.epicapp.memo.ui.editMemory.repository.MemoryEditRepository

class MemoryEditViewModel(private val repository: MemoryEditRepository) : ViewModel() {

    fun saveMemory(memory: MemoryDO) {
        repository.saveMemory(memory)
    }
}

class MemoryEditViewModelFactory(
    private val repository: MemoryEditRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MemoryEditViewModel::class.java)) {
            return MemoryEditViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}