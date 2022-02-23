package com.oddinstitute.crossplatformsvgparser.svg_elements

import android.graphics.Paint

enum class SvgLinecap(val text: String)
{
    BUTT("butt"),
    ROUND("round"),
    SQUARE("square");


    fun toRaw() = enumToRaw[this]
    fun toType() = enumToType[this]

    companion object
    {
        val rawToEnum = mapOf("butt" to BUTT,
                              "round" to ROUND,
                              "square" to SQUARE)
        val enumToRaw = rawToEnum.entries.associate { (k, v) -> v to k }
        fun ofRaw(raw: String): SvgLinecap? = rawToEnum[raw]


        val typeToEnum = mapOf<Paint.Cap, SvgLinecap>(Paint.Cap.SQUARE to SQUARE,
                                                      Paint.Cap.BUTT to BUTT,
                                                      Paint.Cap.ROUND to ROUND)
        val enumToType = typeToEnum.entries.associate { (k, v) -> v to k }

    }
}
