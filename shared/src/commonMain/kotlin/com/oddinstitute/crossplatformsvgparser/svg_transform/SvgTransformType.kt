package com.oddinstitute.crossplatformsvgparser.svg_transform


enum class SvgTransformType(val text: String)
{
    TRANSLATE("translate"),
    ROTATE("rotate"),
    SCALE("scale"),
    MATRIX("matrix"),
    NONE("none");


    fun toRaw() = enumToRaw[this]

    companion object
    {
        val rawToEnum = mapOf("translate" to TRANSLATE,
                              "rotate" to ROTATE,
                              "scale" to SCALE,
                              "matrix" to MATRIX)
        val enumToRaw = rawToEnum.entries.associate { (k, v) -> v to k }
        fun ofRaw(raw: String): SvgTransformType? = rawToEnum[raw]
    }
}
