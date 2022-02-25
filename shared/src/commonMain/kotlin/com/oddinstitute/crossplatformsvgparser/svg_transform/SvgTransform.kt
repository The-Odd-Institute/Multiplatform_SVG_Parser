package com.oddinstitute.crossplatformsvgparser.svg_transform

class SvgTransform()
{
    var type: SvgTransformType = SvgTransformType.TRANSLATE

    // we use the same structure for translate, rotate and scale
    var x: Float = 0f
    var y: Float = 0f

    var angle: Float = 0f
    var cx: Float = 0f
    var cy: Float = 0f

    var matrix: SvgMatrixTransform? = null

    constructor(type: SvgTransformType) : this ()
    {
        this.type = type
    }

}







