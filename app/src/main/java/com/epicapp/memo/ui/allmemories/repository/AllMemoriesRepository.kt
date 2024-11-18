package com.epicapp.memo.ui.allmemories.repository


import com.epicapp.memo.data.network.MemoryDO

class AllMemoriesRepository(
    private val allMemories: MutableList<MemoryDO>
) {
    fun getAllMemories() = allMemories

    fun getFavoriteMemories(): List<MemoryDO> {
        return allMemories.shuffled().take(6)
    }

    fun addMemory(memory: MemoryDO) {
        allMemories.add(memory)
    }

    fun deleteMemory(memory: MemoryDO) {
        allMemories.remove(memory)
    }


}