package com.oddinstitute.crossplatformsvgparser.objects


import com.oddinstitute.crossplatformsvgparser.to_refactor.MyColor
import com.oddinstitute.crossplatformsvgparser.svg_elements.SvgClipRule
import com.oddinstitute.crossplatformsvgparser.svg_elements.SvgLinecap
import com.oddinstitute.crossplatformsvgparser.svg_elements.SvgStrokeLineJoin
import kotlinx.serialization.Transient

class ShapeAttr
{
    // todo - previsouly we thought, these must be initialized
    // these must have default values
    var strokeLineCap : String = "round" // null // = SvgLinecap.ROUND
    var fillType: String? = null // = SvgFillRule.EVENODD

    // Clip rule is not currently supported in this parser
    var clipRule: String? = null // = SvgClipRule.EVENODD
    var dashArray: String? = null
    var strokeLineJoin : String = " miter" // = null // = SvgStrokeLineJoin.MITER


    // fill color after Artwork Transparency has been applied
    @Transient
    var filColorApplied: MyColor? = null

    // these colors were Ints.
    // we changed them to colors
    @Transient
    var fillColor: MyColor? = null
        set(newColor)
        {
            field = newColor
            filColorApplied = newColor
        }

    @Transient
    var strokeColorApplied: MyColor? = null


    @Transient
    var strokeColor: MyColor? = null
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
    var fillColorOrig: MyColor = MyColor()
    var strokeColorOrig: MyColor = MyColor()
    var strokeWidthOrig: Float = 0f
//    var pathValueOrigin: PathValue = PathValue()


}