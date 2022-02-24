package com.oddinstitute.crossplatformsvgparser.svg_elements


enum class SvgClipRule(val text: String)
{
    EVENODD("evenodd"),
    NONZERO("nonzero"),
    INHERIT("inherit");


//    fun toRaw() = enumToRaw[this]
//    fun toType() = enumToType[this]

    companion object
    {
        val rawToEnum = mapOf("evenodd" to EVENODD,
                              "nonzero" to NONZERO)
        val enumToRaw = rawToEnum.entries.associate { (k, v) -> v to k }
        fun ofRaw(raw: String): SvgClipRule? = rawToEnum[raw]


        // currently this does not account for inherit
//        val typeToEnum = mapOf<Path.FillType, SvgClipRule>(
//                Path.FillType.EVEN_ODD to EVENODD,
//                Path.FillType.INVERSE_EVEN_ODD to NONZERO)
//        val enumToType = typeToEnum.entries.associate { (k, v) -> v to k }

    }
}

