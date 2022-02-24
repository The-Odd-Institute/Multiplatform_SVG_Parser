package com.oddinstitute.crossplatformsvgparser

import com.oddinstitute.crossplatformsvgparser.to_refactor.MyColor


// FIXME
actual fun hexToMyColor(colorStr: String): MyColor
{
    val r = 0f // Integer.valueOf(colorStr.substring(1, 3), 16)
    val g = 0f // Integer.valueOf(colorStr.substring(3, 5), 16)
    val b = 0f // Integer.valueOf(colorStr.substring(5, 7), 16)

    return MyColor(r,
                   g,
                   b, 1f)
}
