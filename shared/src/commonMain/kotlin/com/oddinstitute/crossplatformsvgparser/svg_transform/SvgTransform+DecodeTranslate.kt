package com.oddinstitute.crossplatformsvgparser.svg_transform


fun SvgTransform.Decode.decodeTranslate(text: String): SvgTransform
{
    val translate : SvgTransform = SvgTransform(SvgTransformType.TRANSLATE)

    val translateText = text.replace("translate", "")
            .replace("(", "")
            .replace(")", "")
            .trimStart().trimEnd().trim() // we trim the end if there was extra spaces at the end of the tranasform xy
            .replace(" ", ",")

    val translateComponents = translateText.split(",")

    if (translateComponents.count() == 1) // just X
        translate.x = translateComponents[0].toFloat()
    else if (translateComponents.count() == 2)
    {
        translate.x = translateComponents[0].toFloat()
        translate.y = translateComponents[1].toFloat()
    }

    return translate
}