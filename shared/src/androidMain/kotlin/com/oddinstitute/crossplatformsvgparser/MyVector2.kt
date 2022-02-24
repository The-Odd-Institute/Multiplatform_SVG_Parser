package com.oddinstitute.crossplatformsvgparser

import android.graphics.Bitmap
import android.graphics.Matrix
import com.oddinstitute.crossplatformsvgparser.MyColor
import com.oddinstitute.crossplatformsvgparser.MyVector2
import com.oddinstitute.crossplatformsvgparser.operators.roundTwoDecimals
import com.oddinstitute.crossplatformsvgparser.operators.toRadian
import com.oddinstitute.crossplatformsvgparser.svg_transform.SvgMatrixTransform
import java.util.*
import kotlin.math.cos
import kotlin.math.sin



actual class BitmapImage actual constructor()
{
    val bitmap: Bitmap? = null
}


actual class MyColor actual constructor()
{
    // default color is fully transparent white?
    actual var r: Float = 1f
    actual var g: Float = 1f
    actual var b: Float = 1f
    actual var a: Float = 1f

    actual constructor(r: Float, g: Float, b: Float, a: Float?) : this()
    {
        this.r = r
        this.g = g
        this.b = b

        a?.let {
            this.a = it
        }
    }

    actual fun roundTwoDecimals(): MyColor
    {
        val r = this.r.roundTwoDecimals()
        val g = this.g.roundTwoDecimals()
        val b = this.b.roundTwoDecimals()
        val a = this.a.roundTwoDecimals()

        return MyColor(r, g, b, a)
    }
}


actual class UniqueId actual constructor()
{
    actual var id: String = UUID.randomUUID().toString()
}


actual class MyVector2 actual constructor()
{

    actual var x: Float = 0f;
    actual var y: Float = 0f;

    actual constructor(x: Float, y: Float) : this()
    {
        this.x = x
        this.y = y
    }

    actual fun roundTwoDecimals()
    {
        this.x = this.x.roundTwoDecimals()
        this.y = this.y.roundTwoDecimals()
    }

    actual fun scale(scale: MyVector2, pivot: MyVector2)
    {
        this.x = (this.x - pivot.x) * scale.x + pivot.x
        this.y = (this.y - pivot.y) * scale.y + pivot.y
    }

    actual fun translate(offset: MyVector2)
    {
        this.x += offset.x
        this.y += offset.y
    }

    actual fun offset(x: Float, y: Float)
    {
        this.x += x
        this.y += y
    }

    actual fun scale(scaleFactor: Float, pivot: MyVector2)
    {
        this.x = (this.x - pivot.x) * scaleFactor + pivot.x
        this.y = (this.y - pivot.y) * scaleFactor + pivot.y
    }

    actual fun applyMatrixTransform(matrix: SvgMatrixTransform)
    {
        val x = matrix.a * this.x + matrix.c * this.y + matrix.e
        val y = matrix.b * this.x + matrix.d * this.y + matrix.f

        this.x = x
        this.y = y
    }

    actual fun rotate(angle: Float, pivot: MyVector2)
    {
        val x = (cos(angle.toRadian()) * (this.x - pivot.x) - sin(
                angle.toRadian()) * (this.y - pivot.y) + pivot.x).toFloat()
        val y = (sin(angle.toRadian()) * (this.x - pivot.x) + cos(
                angle.toRadian()) * (this.y - pivot.y) + pivot.y).toFloat()

        // the values of this.x and this.y are needed while calculating.
        // that's why we cache the x and y
        this.x = x
        this.y = y
    }

    actual fun times(other: Float): MyVector2
    {
        return MyVector2(this.x * other, this.y * other)
    }

}


actual class MyMatrix actual constructor()
{
    private val m = Matrix()


    actual fun postScale(x: Float, y: Float)
    {
        m.postScale(x, y)
    }

    actual fun postRotate(x: Float)
    {
        m.postRotate(x)
    }

    actual fun postTranslate(x: Float, y: Float)
    {
        m.postTranslate(x, y)
    }

    actual fun mapPoints(arr: FloatArray)
    {
        m.mapPoints(arr)
    }
}