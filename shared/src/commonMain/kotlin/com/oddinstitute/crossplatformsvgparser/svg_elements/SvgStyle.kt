package com.oddinstitute.crossplatformsvgparser.svg_elements

import com.oddinstitute.crossplatformsvgparser.to_refactor.MyColor

class SvgStyle()
{
    var fill: MyColor? = null
    var stroke: MyColor? = null
    var strokeWidth: Float? = null

    var fillRule2: SvgFillRule? = SvgFillRule.NONZERO
    var clipRule: SvgClipRule? = null
    var strokeLineCap: SvgLinecap? = null // butt | round | square
    var strokeDashArray: FloatArray? = null
    var strokeLineJoin: SvgStrokeLineJoin? = null // butt | round | square


    // this function receives a style string and decodes it to actual SvgStyles
    constructor(sText: String) : this()
    {
//        val svgStyle: SvgStyle = SvgStyle()

        // in style, we have both the Equal sign and the colon,
        // we replace equals with a colon
        val components = sText
                .replace("=", ":")
                .replace("\\s+".toRegex(), "") // remove multiple spaces
                .split(";")

        for (each in components)
        {
            val keyVal = each.split(":")
            if (keyVal.count() < 2)
                continue

            val key = keyVal[0]
            val value = keyVal[1]

            when (key)
            {
                "fill" -> this.fill = SvgColor.ofRaw(value)
                "stroke" -> this.stroke = SvgColor.ofRaw(value)
                "stroke-width" -> this.strokeWidth = value.replace("px", "").toFloat() // rare situation
                "fill-rule" -> this.fillRule2 = SvgFillRule.ofRaw(value) // gets from enum
                "clip-rule" -> this.clipRule = SvgClipRule.ofRaw(value)
                "stroke-linecap" -> this.strokeLineCap = SvgLinecap.ofRaw(value)
                "stroke-dasharray" -> this.strokeDashArray = SvgDashArray.ofRaw(value)
                "stroke-linejoin" -> this.strokeLineJoin = SvgStrokeLineJoin.ofRaw(value)
            }
        }
    }
}