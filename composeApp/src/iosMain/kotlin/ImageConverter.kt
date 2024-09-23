import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageBitmapConfig
import androidx.compose.ui.graphics.toPixelMap
import database.Profile
import database.ProfileDao
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.convert
import kotlinx.cinterop.usePinned
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
import platform.CoreGraphics.CGBitmapContextCreate
import platform.CoreGraphics.CGBitmapInfo
import platform.CoreGraphics.CGColorRenderingIntent
import platform.CoreGraphics.CGColorSpaceCreateDeviceRGB
import platform.CoreGraphics.CGContextDrawImage
import platform.CoreGraphics.CGDataProviderCreateWithData
import platform.CoreGraphics.CGImageAlphaInfo
import platform.CoreGraphics.CGImageCreate
import platform.CoreGraphics.CGImageGetBitmapInfo
import platform.CoreGraphics.CGImageGetBitsPerComponent
import platform.CoreGraphics.CGImageGetBytesPerRow
import platform.CoreGraphics.CGImageGetHeight
import platform.CoreGraphics.CGImageGetWidth
import platform.CoreGraphics.CGRectMake
import platform.CoreGraphics.kCGBitmapByteOrderDefault
import platform.Foundation.NSData
import platform.Foundation.NSSearchPathForDirectoriesInDomains
import platform.Foundation.base64EncodedStringWithOptions
import platform.Foundation.create
import platform.Foundation.dataWithBytes
import platform.UIKit.UIApplication
import platform.UIKit.UIImage
import platform.UIKit.UIImagePNGRepresentation
import platform.UIKit.UIViewController
import platform.darwin.UInt32
import platform.posix.memcpy
import utils.ImageProcessor
import utils.ImageSaver

@OptIn(ExperimentalForeignApi::class)
fun uiImageToByteArray(uiImage: UIImage): ByteArray? {
    val data = UIImagePNGRepresentation(uiImage) ?: return null
    val length = data.length.toInt()
    val bytes = ByteArray(length)
    data.bytes?.let { pointer ->
        bytes.usePinned { pinned ->
            memcpy(pinned.addressOf(0), pointer, length.convert())
        }
    }
    return bytes
}
@OptIn(ExperimentalForeignApi::class)
fun ImageBitmap.toUIImage(): UIImage? {
    val pixelMap = this.toPixelMap()
    val width = pixelMap.width
    val height = pixelMap.height

    val colorSpace = CGColorSpaceCreateDeviceRGB()
    val bytesPerPixel = 4
    val bitsPerComponent = 8
    val bytesPerRow = bytesPerPixel * width
    val bitmapDataSize = bytesPerRow * height

    val bitmapData = ByteArray(bitmapDataSize)

    // Copy pixel data into bitmapData
    var index = 0
    for (y in 0 until height) {
        for (x in 0 until width) {
            val color = pixelMap[x, y]
            bitmapData[index++] = (color.red * 255).toInt().toByte()
            bitmapData[index++] = (color.green * 255).toInt().toByte()
            bitmapData[index++] = (color.blue * 255).toInt().toByte()
            bitmapData[index++] = (color.alpha * 255).toInt().toByte()
        }
    }

    val dataProvider = bitmapData.usePinned { pinned ->
        CGDataProviderCreateWithData(
            null,
            pinned.addressOf(0),
            bitmapDataSize.convert(),
            null
        )
    }

    val alphaInfoValue = CGImageAlphaInfo.kCGImageAlphaLast.value
    val bitmapOrderValue = kCGBitmapByteOrderDefault

    val bitmapInfoValue: UInt32 = alphaInfoValue or bitmapOrderValue
    val bitmapInfo = bitmapInfoValue.convert<CGBitmapInfo>()

    val cgImage = CGImageCreate(
        width.convert(),
        height.convert(),
        bitsPerComponent.convert(),
        (bitsPerComponent * bytesPerPixel).convert(),
        bytesPerRow.convert(),
        colorSpace,
        bitmapInfo,
        dataProvider,
        null,
        false,
        CGColorRenderingIntent.kCGRenderingIntentDefault
    )

    return cgImage?.let { UIImage.imageWithCGImage(it) }
}

class ImageSaverImpl : ImageSaver {
    override fun saveImageToDatabase(
        imageBitmap: ImageBitmap?
    ) :ByteArray? {
        val uiImage = imageBitmap!!.toUIImage()
        val imageData = uiImageToByteArray(uiImage!!)
        return imageData
    }
}





