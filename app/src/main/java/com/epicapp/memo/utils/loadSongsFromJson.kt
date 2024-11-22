package com.epicapp.memo.utils

import android.content.Context
import com.epicapp.memo.data.network.SongDO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun loadSongsFromJson(context: Context): List<SongDO> {
    // Lee el archivo JSON como un String
    val json = context.assets.open("songs.json").bufferedReader().use { it.readText() }

    // Usa Gson para deserializar el JSON a una lista de objetos SongDO
    val listType = object : TypeToken<List<SongDO>>() {}.type
    return Gson().fromJson(json, listType)
}
