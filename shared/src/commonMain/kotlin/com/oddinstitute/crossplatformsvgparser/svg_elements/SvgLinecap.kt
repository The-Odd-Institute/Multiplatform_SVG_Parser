package com.oddinstitute.crossplatformsvgparser.svg_elements


data class LineCap (val code: String, val raw: String)

class SvgLinecap(val text: String)
{

    val linecaps = arrayListOf(
            LineCap("BUTT", "butt"),
            LineCap("ROUND", "round"),
            LineCap("SQUARE", "square"))


    fun ofRaw(raw: String) = linecaps.firstOrNull { it.code == raw }

    //    fun toRaw() = enumToRaw[this]
//    fun toType() = enumToType[this]

//    companion object
//    {
//        val rawToEnum = mapOf("butt" to BUTT,
//                              "round" to ROUND,
//                              "square" to SQUARE)
//        val enumToRaw = rawToEnum.entries.associate { (k, v) -> v to k }
//        fun ofRaw(raw: String): SvgLinecap? = rawToEnum[raw]
//
////
////        val typeToEnum = mapOf<Paint.Cap, SvgLinecap>(Paint.Cap.SQUARE to SQUARE,
////                                                      Paint.Cap.BUTT to BUTT,
////                                                      Paint.Cap.ROUND to ROUND)
////        val enumToType = typeToEnum.entries.associate { (k, v) -> v to k }
//
//    }
}
