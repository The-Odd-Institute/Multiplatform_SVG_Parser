package com.oddinstitute.crossplatformsvgparser

import com.oddinstitute.crossplatformsvgparser.operators.roundTwoDecimals
import com.oddinstitute.crossplatformsvgparser.operators.toRadian
import com.oddinstitute.crossplatformsvgparser.svg_transform.SvgMatrixTransform
import kotlin.math.cos
import kotlin.math.sin


class MyVector2 ()
{

    var x: Float = 0f;
    var y: Float = 0f;

    constructor(x: Float, y: Float) : this()
    {
        this.x = x
        this.y = y
    }

    fun roundTwoDecimals()
    {
        this.x = this.x.roundTwoDecimals()
        this.y = this.y.roundTwoDecimals()
    }

    fun scale(scale: MyVector2, pivot: MyVector2)
    {
        this.x = (this.x - pivot.x) * scale.x + pivot.x
        this.y = (this.y - pivot.y) * scale.y + pivot.y
    }

    fun translate(offset: MyVector2)
    {
        this.x += offset.x
        this.y += offset.y
    }

    fun offset(x: Float, y: Float)
    {
        this.x += x
        this.y += y
    }

    fun scale(scaleFactor: Float, pivot: MyVector2)
    {
        this.x = (this.x - pivot.x) * scaleFactor + pivot.x
        this.y = (this.y - pivot.y) * scaleFactor + pivot.y
    }

    fun applyMatrixTransform(matrix: SvgMatrixTransform)
    {
        val x = matrix.a * this.x + matrix.c * this.y + matrix.e
        val y = matrix.b * this.x + matrix.d * this.y + matrix.f

        this.x = x
        this.y = y
    }

    fun rotate(angle: Float, pivot: MyVector2)
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

    fun times(other: Float): MyVector2
    {
        return MyVector2(this.x * other, this.y * other)
    }

}




//import com.oddinstitute.crossplatformsvgparser.svg_transform.SvgMatrixTransform
//
//expect class MyVector2 ()
//{
//    var x: Float
//    var y: Float
//
//    constructor(x: Float, y: Float)
//
//    fun roundTwoDecimals ()
//
//    fun scale(scale: MyVector2,
//              pivot: MyVector2)
//
//
//    fun translate(offset: MyVector2)
//
//    fun offset (x: Float, y: Float )
//
//    fun scale(scaleFactor: Float,
//                     pivot: MyVector2)
//
//    fun applyMatrixTransform(matrix: SvgMatrixTransform)
//
//    fun rotate(angle: Float,
//                      pivot: MyVector2)
//
//
//    fun times(other: Float): MyVector2
//}

