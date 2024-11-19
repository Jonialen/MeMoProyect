package com.epicapp.memo.data.network

data class MemoryDO(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val song: SongDO = SongDO(),
    val date: String = ""
)