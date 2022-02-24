package com.oddinstitute.crossplatformsvgparser

import com.oddinstitute.crossplatformsvgparser.svg_transform.SvgMatrixTransform

expect class MyColor ()
{
    var r: Float
    var g: Float
    var b: Float
    var a: Float

    constructor(r: Float, g: Float, b: Float, a: Float? = 1f)

    fun roundTwoDecimals() : MyColor
}

expect class UniqueId ()
{
    var id: String
}

expect class MyVector2 ()
{
    var x: Float
    var y: Float

    constructor(x: Float, y: Float)

    fun roundTwoDecimals ()

    fun scale(scale: MyVector2,
              pivot: MyVector2)


    fun translate(offset: MyVector2)

    fun offset (x: Float, y: Float )

    fun scale(scaleFactor: Float,
                     pivot: MyVector2)

    fun applyMatrixTransform(matrix: SvgMatrixTransform)

    fun rotate(angle: Float,
                      pivot: MyVector2)


    fun times(other: Float): MyVector2
}

expect class MyMatrix ()
{
    fun postScale(x: Float, y: Float)
    fun postRotate(x: Float)
    fun postTranslate(x: Float, y: Float)
    fun mapPoints(arr: FloatArray)
}