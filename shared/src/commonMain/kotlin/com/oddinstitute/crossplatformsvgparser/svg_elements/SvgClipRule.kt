package com.oddinstitute.crossplatformsvgparser.svg_elements


data class ClipRule(val code: String, val raw: String)


class SvgClipRule(val text: String)
{
    val rules = arrayListOf(
            ClipRule("EVENODD", "evenodd"),
            ClipRule("NONZERO", "nonzero"),
            ClipRule("INHERIT", "inherit"))


//    fun toRaw() = enumToRaw[this]
//    fun toType() = enumToType[this]


//        val rawToEnum = mapOf("evenodd" to EVENODD,
//                              "nonzero" to NONZERO)
//        val enumToRaw = rawToEnum.entries.associate { (k, v) -> v to k }
//        fun ofRaw(raw: String): SvgClipRule? = rawToEnum[raw]

        fun ofRaw(raw: String) = rules.firstOrNull { it.code == raw }


        // currently this does not account for inherit
//        val typeToEnum = mapOf<Path.FillType, SvgClipRule>(
//                Path.FillType.EVEN_ODD to EVENODD,
//                Path.FillType.INVERSE_EVEN_ODD to NONZERO)
//        val enumToType = typeToEnum.entries.associate { (k, v) -> v to k }


}

