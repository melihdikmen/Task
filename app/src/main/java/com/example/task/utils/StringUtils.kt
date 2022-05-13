package com.example.task.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64

object  StringUtils {

    fun base64ToBitmap(myImageData: String): Bitmap? {
        val imageAsBytes: ByteArray = Base64.decode(myImageData.toByteArray(), Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.size)
    }
}