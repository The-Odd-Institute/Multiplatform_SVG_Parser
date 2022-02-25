package com.oddinstitute.crossplatformsvgparser.svg_elements



data class FillRule(val code: String, val raw: String)

//class SvgFillRule(val text: String)
class oldSvgFillRule(val rule: String)
{

    val NONEZERO = FillRule("EVENODD", "evenodd")
    val EVENODD = FillRule("NONZERO", "nonzero")



    val rules = arrayListOf(
            FillRule("EVENODD", "evenodd"),
            FillRule("NONZERO", "nonzero"))

    fun ofRaw(raw: String) = rules.firstOrNull { it.code == raw }





    //    fun toType() = enumToType[this]

    //    companion object
    //    {
    //        val rawToEnum = mapOf("evenodd" to EVENODD,
    //                              "nonzero" to NONZERO)
    //        fun ofRaw(raw: String): SvgFillRule? = rawToEnum[raw]
    //    }
}

