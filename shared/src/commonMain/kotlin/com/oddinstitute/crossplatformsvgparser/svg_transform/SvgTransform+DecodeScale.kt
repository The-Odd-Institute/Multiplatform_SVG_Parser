package com.oddinstitute.crossplatformsvgparser.svg_transform


fun SvgTransform.Decode.decodeScale(text: String): SvgTransform
{
    val scale : SvgTransform = SvgTransform(SvgTransformType.SCALE)

    val scaleText = text.replace("scale", "")
            .replace("(", "")
            .replace(")", "")
            .trimStart().trimEnd().trim() // we trim the end if there was extra spaces at the end of the tranasform xy
            .replace(" ", ",")

    val scaleComponents = scaleText.split(",")

    if (scaleComponents.count() == 1) // just X -> Y is X
    {
        scale.x = scaleComponents[0].toFloat()
        scale.y = scale.x
    }
    else if (scaleComponents.count() == 2)
    {
        scale.x = scaleComponents[0].toFloat()
        scale.y = scaleComponents[1].toFloat()
    }

    return scale
}