package com.oddinstitute.crossplatformsvgparser.svg_elements

import com.oddinstitute.crossplatformsvgparser.operators.toFloatArray
import com.oddinstitute.crossplatformsvgparser.to_refactor.MyColor

class SvgStyle()
{
    var fill: MyColor? = null
    var stroke: MyColor? = null
    var strokeWidth: Float? = null


    // these values shouldn't be initialized. Otherwise, they might overwrite
    // from group to element
    var fillRule: String? = null // "nonzero" // nonzero | evenodd
    var clipRule: String? = null // "nonzero" // nonzero | evenodd | inherit
    var strokeLineCap: String? = null // "butt" // butt | round | square
    var strokeDashArray: String? = null
    var strokeLineJoin: String? = null // "miter" // arcs | bevel |miter | miter-clip | round


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
                "fill" -> this.fill = SvgColor().ofRaw(value)
                "stroke" -> this.stroke = SvgColor().ofRaw(value)
                "stroke-width" -> this.strokeWidth = value.replace("px", "").toFloat() // rare situation
                "fill-rule" -> this.fillRule = value // gets from enum
                "clip-rule" -> this.clipRule = value
                "stroke-linecap" -> this.strokeLineCap = value
                "stroke-dasharray" -> this.strokeDashArray = value
                "stroke-linejoin" -> this.strokeLineJoin = value
            }
        }
    }
}