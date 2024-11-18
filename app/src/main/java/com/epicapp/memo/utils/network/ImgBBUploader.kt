package com.epicapp.memo.utils.network

import android.content.Context
import android.net.Uri
import com.epicapp.memo.data.network.ApiClient
import com.epicapp.memo.data.network.api.ImgBBResponse
import com.epicapp.memo.utils.FileUtils
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

object ImgBBUploader {
    private const val API_KEY = "a4a905a8dd14f03c1957875bc7c4bd95" // Reemplaza con tu API Key

    fun uploadImage(context: Context, imageUri: Uri, onSuccess: (String) -> Unit, onError: (String) -> Unit) {
        try {
            val file = FileUtils.getFileFromUri(context, imageUri)

            if (file.length() > 32 * 1024 * 1024) { // Máximo permitido por ImgBB
                onError("El archivo es demasiado grande. Máximo 32 MB permitido.")
                return
            }

            val requestBody = file.asRequestBody("image/*".toMediaType())
            val multipartBody = MultipartBody.Part.createFormData("image", file.name, requestBody)

            val call = ApiClient.imgbbApi.uploadImage(API_KEY, multipartBody)
            call.enqueue(object : retrofit2.Callback<ImgBBResponse> {
                override fun onResponse(
                    call: retrofit2.Call<ImgBBResponse>,
                    response: retrofit2.Response<ImgBBResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.data?.url?.let(onSuccess)
                    } else {
                        onError("Error al subir la imagen: ${response.message()}")
                    }
                }

                override fun onFailure(call: retrofit2.Call<ImgBBResponse>, t: Throwable) {
                    onError("Fallo de red: ${t.message}")
                }
            })
        } catch (e: Exception) {
            onError("Error inesperado: ${e.message}")
        }
    }
}
