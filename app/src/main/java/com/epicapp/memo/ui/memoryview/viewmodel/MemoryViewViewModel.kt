package com.epicapp.memo.ui.memoryview.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.epicapp.memo.data.network.MemoryDO
import com.epicapp.memo.ui.memoryview.repository.MemoryViewRepository


class MemoryViewViewModel(private val repository: MemoryViewRepository) : ViewModel() {
    fun getMemory(id: String): MemoryDO? {
        return repository.getMemoryById(id)
    }
}

class MemoryViewViewModelFactory(private val repository: MemoryViewRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MemoryViewViewModel::class.java)) {
            return MemoryViewViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}