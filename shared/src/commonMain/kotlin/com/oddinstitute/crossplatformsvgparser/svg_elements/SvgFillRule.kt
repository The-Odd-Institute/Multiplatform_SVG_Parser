package com.oddinstitute.crossplatformsvgparser.svg_elements

enum class SvgFillRule(val text: String)
{
    EVENODD("evenodd"),
    NONZERO("nonzero");

//    fun toType() = enumToType[this]

    companion object
    {
        val rawToEnum = mapOf("evenodd" to EVENODD,
                              "nonzero" to NONZERO)
        fun ofRaw(raw: String): SvgFillRule? = rawToEnum[raw]
    }
}


