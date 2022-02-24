package com.oddinstitute.crossplatformsvgparser.svg_tags.path_tag

import android.graphics.PointF
import com.oddinstitute.crossplatformsvgparser.MyVector2
import com.oddinstitute.crossplatformsvgparser.Segment
import com.oddinstitute.crossplatformsvgparser.SegmentType
import com.oddinstitute.crossplatformsvgparser.operators.times
import com.oddinstitute.crossplatformsvgparser.operators.toFloat


fun PathTag.smoothCurvePiece(piece: String, curPoint: MyVector2, prevSegment: Segment): ArrayList<Segment>
{
    // here, if the first letter was -, then we have like ("s,-4,17.8,-8.9,17.8")
    // so, we check and clean for both "s," and "s"

    val segments = arrayListOf<Segment>()

    val str = piece
            .replace("s", "")
            .replace("S", "")
            .replace(" ", ",")
            .replace(",,", ",") // it's possible to get two commas

    // smooths have 4 pieces, SO
    val points = str
            .split(",")

    // if we are relative, we find the actual value based on the cur point
    // this is simply a shortcut, when relative, we use curPoint, when not, we don't
    // if not, we just add zero
    var intCurPoint = curPoint .times ( (piece[0] == 's').toFloat() )


    for (i in 0 until points.count() step 4)
    {
        val curve = Segment(SegmentType.Curve)

        // if we are relative, we find the actual value based on the cur point
        // this is simply a shortcut, when relative, we use curPoint, when not, we don't
        // if not, we just add zero
        if (piece[0] == 's') // all relative
        {
            intCurPoint = curPoint

            // after the first piece, we should take the previous location
            if (segments.count() > 0)
                intCurPoint = segments.last().v
        }

        curve.i = MyVector2(points[i].toFloat() + intCurPoint.x,
                            points[i+1].toFloat() + intCurPoint.y)

        curve.v = MyVector2(points[i+2].toFloat() + intCurPoint.x,
                             points[i+3].toFloat() + intCurPoint.y)


        /**
         * S
         * Draws a cubic Bézier curve from the current point to (x,y).
         * The first control point is assumed to be the reflection
         * of the second control point on the previous command relative to the current point.
         * If there is no previous command or if the previous command was not an C, c, S or s,
         * assume the first control point is coincident with the current point.
         */

        // todo - this does not count multiple entried in just one

        if (prevSegment.type == SegmentType.Curve)
        {
            var prevCp2X = 0f
            var prevCp2Y = 0f

            prevSegment.i?.let {
                prevCp2X = it.x
                prevCp2Y = it.y
            }


            // these reflections are irrelevant of the relative or not
            // they have to consider the "curPoint", not the "intCurPoint"
            val xReflectionOfSecondCP =
                2 * curPoint.x - prevCp2X // + curX
            val yReflectionOfSecondCP =
                2 * curPoint.y - prevCp2Y // + curY
            curve.o = MyVector2(xReflectionOfSecondCP, yReflectionOfSecondCP)
        }
        else
        {
            curve.o = MyVector2(prevSegment.v.x,
                                prevSegment.v.y)
        }

        segments.add(curve)
    }





    return segments
}