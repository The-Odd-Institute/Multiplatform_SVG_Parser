package com.oddinstitute.crossplatformsvgparser.svg_elements

import android.graphics.Paint


enum class SvgStrokeLineJoin(val text: String)
{
    ARCS ("arcs"),
    BEVEL ("bevel"),
    MITER ("miter"),
    MITERCLIP ("miter-clip"),
    ROUND ("round");


    fun toRaw() = enumToRaw[this]
    fun toType() = enumToType[this]

    companion object
    {
        val rawToEnum = mapOf("arcs" to ARCS,
                              "bevel" to BEVEL,
                              "miter" to MITER,
                              "miter-clip" to MITERCLIP,
                              "round" to ROUND)
        val enumToRaw = rawToEnum.entries.associate { (k, v) -> v to k }
        fun ofRaw(raw: String): SvgStrokeLineJoin? = rawToEnum[raw]


        // todo
        // IMPORTANT
        // it seems like the opposite of the EVENODD is the WINDING
        // In other words, SVG's NONEZERO is Android's WINDING

        val typeToEnum = mapOf<Paint.Join, SvgStrokeLineJoin>(
                Paint.Join.BEVEL to BEVEL,
                Paint.Join.MITER to MITER,
                Paint.Join.ROUND to ROUND,
//                                                              Paint.Join.ROUND to ARCS, // this doesn't exist
//                                                              Paint.Join.ROUND to MITERCLIP // this doesn't exist
                                                             )
        val enumToType = typeToEnum.entries.associate { (k, v) -> v to k }
    }
}
