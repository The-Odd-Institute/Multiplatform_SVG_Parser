package com.oddinstitute.crossplatformsvgparser.svg_tags

import com.oddinstitute.crossplatformsvgparser.MyVector2
import com.oddinstitute.crossplatformsvgparser.to_refactor.Segment
import com.oddinstitute.crossplatformsvgparser.to_refactor.SegmentType
import com.oddinstitute.crossplatformsvgparser.operators.toFloat


fun PathTag.linePiece(piece: String, curPoint: MyVector2): ArrayList<Segment>
{
    val segments = arrayListOf<Segment>()

    val str = piece

            .replace("L", "")
            .replace("l", "")
            .replace("V", "")
            .replace("v", "")
            .replace("H", "")
            .replace("h", "")
            .replace("\\s+".toRegex(), " ") // remove multiple spaces

            .trimStart().trimEnd().trim()

            .replace(" ", ",")
            .replace(",,", ",") // it's possible to get two commas

    val points = str
            .split(",")

    // if we are relative, we find the actual value based on the cur point
    // this is simply a shortcut, when relative, we use curPoint, when not, we don't
    // if not, we just add zero
    var intCurPoint = curPoint.times (  (piece[0] == 'l').toFloat() +
                                    (piece[0] == 'h').toFloat() +
                                    (piece[0] == 'v').toFloat()) // this combination is either zero or one

    when (piece[0])
    {
        // line to new x and y
        'L', 'l' -> {
            for (i in 0 until points.count() step 2)
            {
                val line = Segment(SegmentType.Line)

                if (piece[0] == 'l' && segments.count() > 0)
                    intCurPoint = segments.last().v

                line.v = MyVector2(points[i].toFloat() + intCurPoint.x, points[i+1].toFloat() + intCurPoint.y)
                segments.add(line)
            }
        }


        // line from current point horizontally relative to the new x
        'h', 'H' ->
        {
            for (i in 0 until points.count() step 2)
            {
                val line = Segment(SegmentType.Line)

                if (piece[0] == 'h' && segments.count() > 0)
                    intCurPoint = segments.last().v

                line.v = MyVector2(points[0].toFloat() + intCurPoint.x, intCurPoint.y)
                segments.add(line)
            }
        }


        // THESE TWO ARE ALSO points[0], because there is only one value after H or V
        // line from current point vertically relative to the new y
        'v', 'V' ->{
            for (i in 0 until points.count() step 2)
            {
                val line = Segment(SegmentType.Line)

                if (piece[0] == 'v' && segments.count() > 0)
                    intCurPoint = segments.last().v

                line.v = MyVector2(intCurPoint.x, points[0].toFloat() + intCurPoint.y)
                segments.add(line)
            }
        }
    }


    return segments
}