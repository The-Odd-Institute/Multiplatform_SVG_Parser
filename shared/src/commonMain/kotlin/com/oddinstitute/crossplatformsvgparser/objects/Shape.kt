package com.oddinstitute.crossplatformsvgparser.objects

import android.graphics.Color
import android.graphics.Path
import android.graphics.Paint
import kotlinx.serialization.Transient

class Shape
{
    // these must have default values
    var strokeLineCap : Paint.Cap = Paint.Cap.ROUND
    var fillType: Path.FillType = Path.FillType.EVEN_ODD

    // Clip rule is not currently supported in this parser
    var clipRule: Path.FillType = Path.FillType.EVEN_ODD
    var dashArray: FloatArray? = null
    var strokeLineJoin : Paint.Join = Paint.Join.MITER


    // fill color after Artwork Transparency has been applied
    @Transient
    var filColorApplied: Color = Color.valueOf(Color.TRANSPARENT)

    // these colors were Ints.
    // we changed them to colors
    @Transient
    var fillColor: Color = Color.valueOf(Color.TRANSPARENT)
        set(newColor)
        {
            field = newColor
            filColorApplied = newColor
        }

    @Transient
    var strokeColorApplied: Color = Color.valueOf(Color.TRANSPARENT)


    @Transient
    var strokeColor: Color = Color.valueOf(Color.TRANSPARENT)
        set(newColor)
        {
            field = newColor
            strokeColorApplied = newColor
        }


    var strokeWidth: Float = 0f


//    var pathValue: PathValue = PathValue()


    // these are the internal values of the polygon
    // they are maintained for when we add new motion bundles
    // todo ALL COLORS HAVE been replaced from Int to Color
    var fillColorOrig: Color = Color.valueOf(Color.TRANSPARENT)
    var strokeColorOrig: Color = Color.valueOf(Color.TRANSPARENT)
    var strokeWidthOrig: Float = 0f
//    var pathValueOrigin: PathValue = PathValue()


}
