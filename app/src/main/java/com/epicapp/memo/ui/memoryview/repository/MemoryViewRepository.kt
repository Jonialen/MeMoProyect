package com.epicapp.memo.ui.memoryview.repository

import com.epicapp.memo.data.network.MemoryDO
import com.epicapp.memo.data.network.repository.MemoryRepository

class MemoryViewRepository(val memoryRepository: MemoryRepository) {

    // Obtener una memoria por ID desde Firestore
    suspend fun getMemoryById(id: String): MemoryDO? {
        val allMemories = memoryRepository.getMemories() // Cargar todas las memorias desde Firestore
        return allMemories.find { it.id == id }
    }
}
