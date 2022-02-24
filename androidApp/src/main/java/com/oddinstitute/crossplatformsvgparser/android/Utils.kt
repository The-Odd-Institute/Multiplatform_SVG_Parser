package com.oddinstitute.crossplatformsvgparser.android

import android.graphics.Color
import com.oddinstitute.crossplatformsvgparser.MyColor

class Utils
{
    companion object
    {
        fun myColorToArgb (myColor: MyColor) : Int
        {
            val color = Color.valueOf(myColor.r,
                                     myColor.g,
                                     myColor.b,
                                     myColor.a)

            return color.toArgb()
        }
    }

}