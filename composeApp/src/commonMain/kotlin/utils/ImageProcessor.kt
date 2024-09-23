package utils

interface ImageProcessor {
    fun processImage(byteArray: ByteArray?): Any? // `Any?` будет `UIImage?` для iOS и `Bitmap?` для Android
}