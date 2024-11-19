package com.epicapp.memo.ui.allmemories.repository

import com.epicapp.memo.data.network.MemoryDO
import com.epicapp.memo.data.network.repository.MemoryRepository

class AllMemoriesRepository(
    private val memoryRepository: MemoryRepository
) {

    // Obtener todas las memorias desde Firestore
    suspend fun getAllMemories(): List<MemoryDO> {
        return memoryRepository.getMemories()
    }
}
