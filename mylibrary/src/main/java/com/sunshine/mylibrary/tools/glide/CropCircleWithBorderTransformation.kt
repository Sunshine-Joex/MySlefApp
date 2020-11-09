package com.sunshine.mylibrary.tools.glide

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.annotation.ColorInt
import com.bumptech.glide.load.Key
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.TransformationUtils
import java.security.MessageDigest

class CropCircleWithBorderTransformation(val borderSize: Int, val borderColor: Int) :
    BitmapTransformation() {

    override fun transform(
        context: Context, pool: BitmapPool,
        toTransform: Bitmap, outWidth: Int, outHeight: Int
    ): Bitmap {
        val bitmap = TransformationUtils.circleCrop(pool, toTransform, outWidth, outHeight)
        setCanvasBitmapDensity(toTransform, bitmap)
        val paint = Paint()
        paint.color = borderColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = borderSize.toFloat()
        paint.isAntiAlias = true
        val canvas = Canvas(bitmap)
        canvas.drawCircle(
            outWidth / 2f,
            outHeight / 2f,
            Math.max(outWidth, outHeight) / 2f - borderSize / 2f,
            paint
        )
        return bitmap
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update(
            (ID + borderSize + borderColor).toByteArray(
                Key.CHARSET
            )
        )
    }

    override fun equals(o: Any?): Boolean {
        return o is CropCircleWithBorderTransformation && o.borderSize == borderSize && o.borderColor == borderColor
    }

    override fun hashCode(): Int {
        return ID.hashCode() + borderSize * 100 + borderColor + 10
    }

    companion object {
        private const val VERSION = 1
        private const val ID =
            "jp.wasabeef.glide.transformations.CropCircleWithBorderTransformation.$VERSION"
    }
}