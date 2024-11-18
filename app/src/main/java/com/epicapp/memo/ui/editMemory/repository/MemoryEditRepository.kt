package com.epicapp.memo.ui.editMemory.repository

import androidx.compose.runtime.mutableStateListOf
import com.epicapp.memo.data.network.MemoryDO

class MemoryEditRepository(private val allMemories: MutableList<MemoryDO>) {

    fun saveMemory(memory: MemoryDO) {
        val index = allMemories.indexOfFirst { it.id == memory.id }
        if (index != -1) {
            allMemories[index] = memory
        } else {
            allMemories.add(memory)
        }
    }
}