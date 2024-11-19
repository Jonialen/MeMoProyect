package com.epicapp.memo.data.network.repository

import android.util.Log
import com.epicapp.memo.data.network.MemoryDO
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class MemoryRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    // Obtener la colección de recuerdos del usuario autenticado
    private fun getUserMemoriesCollection() =
        firestore.collection("users").document(auth.currentUser?.uid ?: "")
            .collection("memories")

    // Obtener recuerdos del usuario con logs para depuración
    suspend fun getMemories(): List<MemoryDO> {
        try {
            val result = getUserMemoriesCollection().get().await()

            // Log para cada documento recuperado
            result.documents.forEach { document ->
                Log.d("MEMORIAS_GET","Documento ID: ${document.id}")
                Log.d("MEMORIAS_GET","Datos crudos: ${document.data}")
            }

            // Mapeo a MemoryDO y manejo de posibles errores
            val mappedMemories = result.documents.mapNotNull { document ->
                try {
                    val memory = document.toObject(MemoryDO::class.java)
                    Log.d("MEMORIAS_GET","Memoria mapeada: $memory")
                    memory
                } catch (e: Exception) {
                    Log.d("MEMORIAS_GET","Error al mapear documento ID ${document.id}: ${e.message}")
                    null
                }
            }

            return mappedMemories
        } catch (e: Exception) {
            Log.d("MEMORIAS_GET","Error al obtener recuerdos: ${e.message}")
            return emptyList()
        }
    }


    // Agregar un recuerdo
    suspend fun saveMemory(memory: MemoryDO) {
        getUserMemoriesCollection().document(memory.id).set(memory).await()
    }


    // Eliminar un recuerdo
    suspend fun deleteMemory(memoryId: String) {
        try {
            Log.d("DELETE_MEMORY", "Intentando eliminar memoria con ID: $memoryId")
            getUserMemoriesCollection().document(memoryId).delete().await()
            Log.d("DELETE_MEMORY", "Memoria eliminada exitosamente: $memoryId")
        } catch (e: Exception) {
            Log.e("DELETE_MEMORY", "Error al eliminar memoria: ${e.localizedMessage}")
            throw RuntimeException("Error al eliminar memoria: ${e.localizedMessage}")
        }
    }

}
