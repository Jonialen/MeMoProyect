package com.epicapp.memo.ui.memoryview.repository

import com.epicapp.memo.data.network.MemoryDO

class MemoryViewRepository(private val allMemories: List<MemoryDO>) {
    fun getMemoryById(id: String): MemoryDO? {
        return allMemories.find { it.id == id }
    }
}