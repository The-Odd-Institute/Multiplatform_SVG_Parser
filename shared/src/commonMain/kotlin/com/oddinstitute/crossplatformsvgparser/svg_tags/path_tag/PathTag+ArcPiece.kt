package com.oddinstitute.crossplatformsvgparser.svg_tags.path_tag

import android.graphics.PointF
import com.oddinstitute.crossplatformsvgparser.Segment
import com.oddinstitute.crossplatformsvgparser.SevenPieceArc
import com.oddinstitute.crossplatformsvgparser.operators.times
import com.oddinstitute.crossplatformsvgparser.operators.toBoolean
import com.oddinstitute.crossplatformsvgparser.operators.toFloat
import com.oddinstitute.crossplatformsvgparser.toSegmentsObjCMethod

fun PathTag.arcPieces(piece: String, curPoint: PointF): ArrayList<Segment>
{
    val str = piece
            .replace("a", "")
            .replace("A", "")
            .replace(" ", ",")
            .replace(",,", ",") // it's possible to get two commas
    // at this level, you can't move this to CLEAN

    val points = str
            .split(",")

    if (points.count() != 7)
        return ArrayList<Segment>()

    val rx = points[0].toFloat()
    val ry = points[1].toFloat()

    val xAxisRotation = points[2].toFloat()
    val largeArcFlag_fA = points[3].toInt().toBoolean()
    val sweepFlag_fS = points[4].toInt().toBoolean()

    // if we are relative, we find the actual value based on the cur point
    // this is simply a shortcut, when relative, we use curPoint, when not, we don't
    // if not, we just add zero
    val intCurPoint = curPoint * (piece[0] == 'a').toFloat()

    val x2 = points[5].toFloat() + intCurPoint.x
    val y2 = points[6].toFloat() + intCurPoint.y

    val s = SevenPieceArc(rx, ry, xAxisRotation, largeArcFlag_fA, sweepFlag_fS, x2, y2)

//    val segments: ArrayList<NewSegment> = s.toSegmentsJavaMethod(curPoint)

    return s.toSegmentsObjCMethod(curPoint)
}

