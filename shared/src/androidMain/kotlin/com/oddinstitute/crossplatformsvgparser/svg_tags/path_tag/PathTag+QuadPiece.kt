package com.oddinstitute.crossplatformsvgparser.svg_tags.path_tag

import com.oddinstitute.crossplatformsvgparser.MyVector2
import com.oddinstitute.crossplatformsvgparser.Segment
import com.oddinstitute.crossplatformsvgparser.operators.toFloat


fun PathTag.quadPiece(piece: String, curPoint: MyVector2): ArrayList<Segment>
{

    val segments = arrayListOf<Segment>()
    val str = piece
            .replace("q", "")
            .replace("Q", "")
            .replace(" ", ",")
            .replace(",,", ",") // it's possible to get two commas

    // quads have 4 pieces, SO
    val points = str
            .split(",")

    // if we are relative, we find the actual value based on the cur point
    // this is simply a shortcut, when relative, we use curPoint, when not, we don't
    // if not, we just add zero
    var intCurPoint = curPoint.times ((piece[0] == 'q').toFloat())


    for (i in 0 until points.count() step 4)
    {
        // if we are relative, we find the actual value based on the cur point
        // this is simply a shortcut, when relative, we use curPoint, when not, we don't
        // if not, we just add zero
        if (piece[0] == 'q') // all relative
        {
            intCurPoint = curPoint

            // after the first piece, we should take the previous location
            if (segments.count() > 0)
                intCurPoint = segments.last().v
        }

        val control = MyVector2(points[i].toFloat() + intCurPoint.x,
                          points[i+1].toFloat() + intCurPoint.y)

        val end = MyVector2(points[i+2].toFloat() + intCurPoint.x,
                   points[i+3].toFloat() + intCurPoint.y)

        val curve = Segment.quadToCurve(intCurPoint, control, end)

        segments.add(curve)
    }

    return segments
}