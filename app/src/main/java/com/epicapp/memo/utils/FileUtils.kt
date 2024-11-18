package com.epicapp.memo.utils

import android.content.Context
import android.net.Uri
import java.io.File
import java.io.FileOutputStream

object FileUtils {
    fun getFileFromUri(context: Context, uri: Uri): File {
        val inputStream = context.contentResolver.openInputStream(uri)
            ?: throw IllegalArgumentException("No se pudo abrir el InputStream para el URI proporcionado.")

        val file = File(context.cacheDir, "upload_image.jpg")
        val outputStream = FileOutputStream(file)
        inputStream.use { input -> outputStream.use { output -> input.copyTo(output) } }

        return file
    }
}
