package com.oddinstitute.crossplatformsvgparser.operators

import android.graphics.Color
import kotlin.math.roundToInt

fun Float.roundTwoDecimals(): Float
{
    return (this * 100.0).roundToInt() / 100.0f
}

fun Color.roundTwoDecimals() : Color
{
    val r = this.red().roundTwoDecimals()
    val g = this.green().roundTwoDecimals()
    val b = this.blue().roundTwoDecimals()
    val a = this.alpha().roundTwoDecimals()


    return Color.valueOf(r, g, b, a)

}