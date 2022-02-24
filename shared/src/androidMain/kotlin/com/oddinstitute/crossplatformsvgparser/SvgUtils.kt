package com.oddinstitute.crossplatformsvgparser

import android.graphics.Paint
import android.graphics.Path
import com.oddinstitute.crossplatformsvgparser.to_refactor.MyColor
import com.oddinstitute.crossplatformsvgparser.svg_elements.SvgFillRule
import com.oddinstitute.crossplatformsvgparser.svg_elements.SvgLinecap
import com.oddinstitute.crossplatformsvgparser.svg_elements.SvgStrokeLineJoin


// Android ONLY
actual fun hexToMyColor(colorStr: String): MyColor
{
    val r = Integer.valueOf(colorStr.substring(1, 3), 16)
    val g = Integer.valueOf(colorStr.substring(3, 5), 16)
    val b = Integer.valueOf(colorStr.substring(5, 7), 16)

    return MyColor(r.toFloat() / 255f,
                   g.toFloat() / 255f,
                   b.toFloat() / 255f, 1f)
}


fun SvgLineJoinToType(rule: SvgStrokeLineJoin): Paint.Join?
{ // todo
    // IMPORTANT
    // it seems like the opposite of the EVENODD is the WINDING
    // In other words, SVG's NONEZERO is Android's WINDING

    val typeToEnum = mapOf<Paint.Join, SvgStrokeLineJoin>(
            Paint.Join.BEVEL to SvgStrokeLineJoin.BEVEL,
            Paint.Join.MITER to SvgStrokeLineJoin.MITER,
            Paint.Join.ROUND to SvgStrokeLineJoin.ROUND)
    //                                                              Paint.Join.ROUND to ARCS, // this doesn't exist
    //                                                              Paint.Join.ROUND to MITERCLIP // this doesn't exist

    val enumToType = typeToEnum.entries.associate { (k, v) -> v to k }

    return enumToType[rule]

    // todo
    // IMPORTANT
    // it seems like the opposite of the EVENODD is the WINDING
    // In other words, SVG's NONEZERO is Android's WINDING


}



fun SvgLineCapToType(rule: SvgLinecap): Paint.Cap?
{ // todo
    // IMPORTANT
    // it seems like the opposite of the EVENODD is the WINDING
    // In other words, SVG's NONEZERO is Android's WINDING

    val typeToEnum = mapOf<Paint.Cap, SvgLinecap>(Paint.Cap.SQUARE to SvgLinecap.SQUARE,
                                                  Paint.Cap.BUTT to SvgLinecap.BUTT,
                                                  Paint.Cap.ROUND to SvgLinecap.ROUND)

    val enumToType = typeToEnum.entries.associate { (k, v) -> v to k }

    return enumToType[rule]
}

fun SvgFillRuleToType(rule: SvgFillRule): Path.FillType?
{ // todo
    // IMPORTANT
    // it seems like the opposite of the EVENODD is the WINDING
    // In other words, SVG's NONEZERO is Android's WINDING

    val typeToEnum = mapOf<Path.FillType, SvgFillRule>(Path.FillType.EVEN_ODD to SvgFillRule.EVENODD,
                                                       Path.FillType.WINDING to SvgFillRule.NONZERO)
    val enumToType = typeToEnum.entries.associate { (k, v) -> v to k }

    return enumToType[rule]
}
