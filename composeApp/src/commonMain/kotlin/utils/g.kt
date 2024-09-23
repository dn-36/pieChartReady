package utils

import androidx.compose.ui.graphics.ImageBitmap
import database.ProfileDao

interface ImageSaver {
    fun saveImageToDatabase(
        imageBitmap: ImageBitmap?
    ):ByteArray?
}