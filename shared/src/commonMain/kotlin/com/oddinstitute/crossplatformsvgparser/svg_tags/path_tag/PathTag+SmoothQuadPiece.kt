package com.oddinstitute.crossplatformsvgparser.svg_tags.path_tag

import android.graphics.PointF
import com.oddinstitute.crossplatformsvgparser.Segment
import com.oddinstitute.crossplatformsvgparser.SegmentType
import com.oddinstitute.crossplatformsvgparser.operators.times
import com.oddinstitute.crossplatformsvgparser.operators.toFloat

fun PathTag.smoothQuadPiece(piece: String, curPoint: PointF, prevSegment: Segment): Segment
{
    val str = piece
            .replace(" ", ",")
            .replace("t", "")
            .replace("T", "")
            .replace(" ", ",")
            .replace(",,", ",") // it's possible to get two commas

    val points = str
            .split(",")

    // if we are relative, we find the actual value based on the cur point
    // this is simply a shortcut, when relative, we use curPoint, when not, we don't
    // if not, we just add zero
    val intCurPoint = curPoint * (piece[0] == 't').toFloat()



    val end = PointF(points[0].toFloat() + intCurPoint.x,
                   points[1].toFloat() + intCurPoint.y)

    var control : PointF

    /**
     * T
     * Draws a quadratic Bézier curve from the current point to (x,y).
     * The control point is assumed to be the reflection of the control point
     * on the previous command relative to the current point.
     * If there is no previous command or if the previous command was not a Q, q, T or t,
     * assume the control point is coincident with the current point
     */

    if (prevSegment.type == SegmentType.Curve)
    {
        var prevCp1X = 0f
        var prevCp1Y = 0f

        prevSegment.o?.let {
            prevCp1X = it.x
            prevCp1Y = it.y
        }


        // these reflections are irrelevant of the relative or not
        // they have to consider the "curPoint", not the "intCurPoint"
        val xReflectionOfFirstCP =
            2 * curPoint.x - prevCp1X //+ curX
        val yReflectionOfFirstCP =
            2 * curPoint.y - prevCp1Y //+ curY

        control = PointF(xReflectionOfFirstCP, yReflectionOfFirstCP)
    }
    else
    {
        control =
            PointF(prevSegment.v.x, prevSegment.v.y)
    }


    val curve = Segment.quadToCurve(intCurPoint, control, end)

    return curve
}