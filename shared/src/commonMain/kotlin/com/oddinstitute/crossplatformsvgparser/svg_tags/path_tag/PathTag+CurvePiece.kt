package com.oddinstitute.crossplatformsvgparser.svg_tags.path_tag

import android.graphics.PointF
import com.oddinstitute.crossplatformsvgparser.Segment
import com.oddinstitute.crossplatformsvgparser.SegmentType

//Multiple sets of coordinates may be specified to draw a polybézier.
//At the end of the command, the new current point becomes the final (x,y)
//coordinate pair used in the polybézier.

fun PathTag.curvePiece(piece: String, curPoint: PointF): ArrayList<Segment>
{
    val segments = arrayListOf<Segment>()

    val str = piece
            .replace("c", "")
            .replace("C", "")
            .replace("\\s+".toRegex(), " ") // remove multiple spaces
            .trimStart().trimEnd().trim()

            .replace(" ", ",")
            .replace(",,", ",") // it's possible to get two commas

    var intCurPoint = PointF() // this is either the current point or the knot of the last segments

    // curves have multiples of 6 pieces, SO
    val initialPoints : List<String> = str.split(",")

    val points = cleanPointsOfDecimals(initialPoints)

    for (i in 0 until points.count() step 6)
    {
        val curve = Segment(SegmentType.Curve)

        // if we are relative, we find the actual value based on the cur point
        // this is simply a shortcut, when relative, we use curPoint, when not, we don't
        // if not, we just add zero
        if (piece[0] == 'c') // all relative
        {
            intCurPoint = curPoint

            // after the first piece, we should take the previous location
            if (segments.count() > 0)
                intCurPoint = segments.last().v
        }

        curve.o = PointF(points[i].toFloat() + intCurPoint.x,
                           points[i+1].toFloat() + intCurPoint.y)

        curve.i = PointF(points[i+2].toFloat() + intCurPoint.x,
                           points[i+3].toFloat() + intCurPoint.y)

        curve.v = PointF(points[i+4].toFloat() + intCurPoint.x,
                            points[i+5].toFloat() + intCurPoint.y)

        segments.add(curve)
    }

    return segments
}


