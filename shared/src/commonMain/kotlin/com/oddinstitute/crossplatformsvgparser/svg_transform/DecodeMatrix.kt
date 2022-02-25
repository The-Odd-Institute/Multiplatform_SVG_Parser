package com.oddinstitute.crossplatformsvgparser.svg_transform


fun SvgTransform. decodeMatrix(text: String): SvgTransform
{
    val trans : SvgTransform = SvgTransform(SvgTransformType.MATRIX)

    val scaleText = text.replace("matrix", "")
            .replace("(", "")
            .replace(")", "")
            .trimStart().trimEnd().trim() // we trim the end if there was extra spaces at the end of the tranasform xy
            .replace(" ", ",")

    val matrixComponents = scaleText.split(",")

    val a = matrixComponents[0].toFloat()
    val b = matrixComponents[1].toFloat()
    val c = matrixComponents[2].toFloat()
    val d = matrixComponents[3].toFloat()
    val e = matrixComponents[4].toFloat()
    val f = matrixComponents[5].toFloat()

    trans.matrix = SvgMatrixTransform(a, b, c, d, e, f)

    return trans
}