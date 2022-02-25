package com.oddinstitute.crossplatformsvgparser.svg_transform


fun SvgTransform. decodeRotate(text: String): SvgTransform
{
    val rotate : SvgTransform = SvgTransform(SvgTransformType.ROTATE)

    val rotateText = text.replace("rotate", "")
            .replace("(", "")
            .replace(")", "")
            .trimStart().trimEnd().trim() // we trim the end if there was extra spaces at the end of the tranasform xy
            .replace(" ", ",")

    val rotateComponents = rotateText.split(",")

    if (rotateComponents.count() == 1) // just angle
        rotate.angle = rotateComponents[0].toFloat()
    else if (rotateComponents.count() == 3)
    {
        rotate.angle = rotateComponents[0].toFloat()
        rotate.cx = rotateComponents[1].toFloat()
        rotate.cy = rotateComponents[2].toFloat()
    }

    return rotate
}
