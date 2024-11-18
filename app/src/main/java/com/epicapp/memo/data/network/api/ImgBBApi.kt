package com.epicapp.memo.data.network.api

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface ImgBBApi {
    @Multipart
    @POST("1/upload")
    fun uploadImage(
        @Query("key") apiKey: String,
        @Part image: MultipartBody.Part
    ): Call<ImgBBResponse>
}

data class ImgBBResponse(
    val data: ImgBBData,
    val success: Boolean,
    val status: Int
)

data class ImgBBData(
    val url: String
)
