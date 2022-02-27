package com.oddinstitute.crossplatformsvgparser.android

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import com.oddinstitute.crossplatformsvgparser.to_refactor.MyColor

class Utils
{
    companion object
    {
        fun myColorToArgb(myColor: MyColor) : Int
        {
            val color = Color.valueOf(myColor.r,
                                     myColor.g,
                                     myColor.b,
                                     myColor.a)

            return color.toArgb()
        }



        fun svgLineJoinToType(text: String): Paint.Join
        { // todo
            // IMPORTANT
            // it seems like the opposite of the EVENODD is the WINDING
            // In other words, SVG's NONEZERO is Android's WINDING

            //  arcs | bevel |miter | miter-clip | round

            val stringToType = mapOf<String, Paint.Join>(
                    "round" to Paint.Join.ROUND,
                    "bevel" to Paint.Join.BEVEL,
                    "miter" to Paint.Join.MITER)


            stringToType[text]?.let { return it }

            return Paint.Join.MITER // default is miter, so, if miter is not found, then do this

            //    val typeToEnum = mapOf<Paint.Join, String>(
            //            Paint.Join.BEVEL to SvgStrokeLineJoin.BEVEL,
            //            Paint.Join.MITER to SvgStrokeLineJoin.MITER,
            //            Paint.Join.ROUND to SvgStrokeLineJoin.ROUND)
            //    //                                                              Paint.Join.ROUND to ARCS, // this doesn't exist
            //    //                                                              Paint.Join.ROUND to MITERCLIP // this doesn't exist
            //
            //    val enumToType = typeToEnum.entries.associate { (k, v) -> v to k }
            //
            //    return enumToType[rule]

            // todo
            // IMPORTANT
            // it seems like the opposite of the EVENODD is the WINDING
            // In other words, SVG's NONEZERO is Android's WINDING


        }



        fun svgLineCapToType(text: String): Paint.Cap
        { // todo
            // IMPORTANT
            // it seems like the opposite of the EVENODD is the WINDING
            // In other words, SVG's NONEZERO is Android's WINDING

            // butt | round | square
            val stringToType = mapOf<String, Paint.Cap>(
                    "round" to Paint.Cap.ROUND,
                    "butt" to Paint.Cap.BUTT,
                    "square" to Paint.Cap.SQUARE)

            stringToType[text]?.let { return it }

            return Paint.Cap.BUTT // default is miter, so, if miter is not found, then do this
        }

        fun svgFillRuleToType(text: String): Path.FillType
        { // nonzero | evenodd
            val stringToType = mapOf("nonzero" to Path.FillType.WINDING,
                                     "evenodd" to Path.FillType.EVEN_ODD)

            stringToType[text]?.let { return it }

            return Path.FillType.WINDING //    return Path.FillType.BUTT // default is miter, so, if miter is not found, then do this
            //
            //    val typeToEnum = mapOf<Path.FillType, SvgFillRule>(Path.FillType.EVEN_ODD to SvgFillRule.EVENODD,
            //                                                       Path.FillType.WINDING to SvgFillRule.NONZERO)
            //    val enumToType = typeToEnum.entries.associate { (k, v) -> v to k }
            //
            //    return enumToType[rule]
        }

    }

}