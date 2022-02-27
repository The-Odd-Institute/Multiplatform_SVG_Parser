package com.oddinstitute.crossplatformsvgparser

import com.oddinstitute.crossplatformsvgparser.to_refactor.MyColor


fun String.hexToColor() : MyColor
{
    val hexR = this.substring(1, 3)
    val hexG = this.substring(3, 5)
    val hexB = this.substring(5, 7)


    val  r = hexR.toInt(16)
    val  g = hexG.toInt(16)
    val  b = hexB.toInt(16)


    //    val r = Integer.valueOf(, 16)
    //    val g = Integer.valueOf(colorStr.substring(3, 5), 16)
    //    val b = Integer.valueOf(colorStr.substring(5, 7), 16)

    return MyColor(r.toFloat() / 255f,
                   g.toFloat() / 255f,
                   b.toFloat() / 255f, 1f)
}

