package com.oddinstitute.crossplatformsvgparser.svg_tags

import com.oddinstitute.crossplatformsvgparser.svg_elements.*
import com.oddinstitute.crossplatformsvgparser.svg_transform.SvgTransform
import com.oddinstitute.crossplatformsvgparser.to_refactor.MyColor

class SvgAttributesBundle
{
    var id: String? = null

    var fill: MyColor? = null

    var stroke: MyColor? = null
    var strokeWidth: Float? = null

    var fillRule: SvgFillRule? = null
    var clipRule: SvgClipRule? = null
    var strokeLineCap: SvgLinecap? = null // butt | round | square
    var strokeLineJoin: SvgStrokeLineJoin? = null
    var strokeDashArray: FloatArray? = null // butt | round | square

    var transforms: ArrayList<SvgTransform>? = null

    var style: SvgStyle? = null
    var svgClass: String? = null // this is the only one that's a string.
    // it makes sense (it's not a first tag)

    // this is generic, but only used in the actual tags
    // var parentGroup: GTag? = null
}




