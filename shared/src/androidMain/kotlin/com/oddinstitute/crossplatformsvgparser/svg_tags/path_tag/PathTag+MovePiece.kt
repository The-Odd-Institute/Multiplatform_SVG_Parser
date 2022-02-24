package com.oddinstitute.crossplatformsvgparser.svg_tags.path_tag

import com.oddinstitute.crossplatformsvgparser.MyVector2
import com.oddinstitute.crossplatformsvgparser.Segment
import com.oddinstitute.crossplatformsvgparser.SegmentType
import com.oddinstitute.crossplatformsvgparser.operators.toFloat

fun PathTag.movePiece(piece: String, curPoint: MyVector2): Segment
{
    val str = piece.replace("m", "")

            .replace("M", "")
            .replace("\\s+".toRegex(), " ") // remove multiple spaces
            .trim().trimStart().trimEnd()
            .replace(" ", ",")
            .replace(",,", ",") // it's possible to get two commas


    val points = str
            .split(",")
    val move: Segment = Segment(SegmentType.Move)


    // if we are relative, we find the actual value based on the cur point
    // this is simply a shortcut, when relative, we use curPoint, when not, we don't
    // if not, we just add zero
    val intCurPoint = curPoint.times( (piece[0] == 'm').toFloat())

    move.v = MyVector2(points[0].toFloat() + intCurPoint.x,
                    points[1].toFloat() + intCurPoint.y)

    return move
}



