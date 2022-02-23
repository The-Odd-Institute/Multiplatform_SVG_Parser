package com.oddinstitute.crossplatformsvgparser.svg_elements

import android.graphics.Paint
import android.graphics.Path

enum class SvgFillRule(val text: String)
{
    EVENODD("evenodd"),
    NONZERO("nonzero");


    fun toRaw() = enumToRaw[this]
    fun toType() = enumToType[this]

    companion object
    {
        val rawToEnum = mapOf("evenodd" to EVENODD,
                              "nonzero" to NONZERO)
        val enumToRaw = rawToEnum.entries.associate { (k, v) -> v to k }
        fun ofRaw(raw: String): SvgFillRule? = rawToEnum[raw]


        // todo
        // IMPORTANT
        // it seems like the opposite of the EVENODD is the WINDING
        // In other words, SVG's NONEZERO is Android's WINDING

        val typeToEnum = mapOf<Path.FillType, SvgFillRule>(Path.FillType.EVEN_ODD to EVENODD,
                                                           Path.FillType.WINDING to NONZERO)
        val enumToType = typeToEnum.entries.associate { (k, v) -> v to k }
    }
}


