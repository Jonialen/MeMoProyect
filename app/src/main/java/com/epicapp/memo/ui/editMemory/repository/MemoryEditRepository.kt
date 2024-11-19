package com.epicapp.memo.ui.editMemory.repository

import com.epicapp.memo.data.network.MemoryDO
import com.epicapp.memo.data.network.repository.MemoryRepository

class MemoryEditRepository(private val memoryRepository: MemoryRepository) {

    suspend fun saveMemory(memory: MemoryDO) {
        memoryRepository.saveMemory(memory)
    }

    suspend fun getMemoryById(memoryId: String): MemoryDO? {
        val memories = memoryRepository.getMemories()
        return memories.find { it.id == memoryId }
    }
}
