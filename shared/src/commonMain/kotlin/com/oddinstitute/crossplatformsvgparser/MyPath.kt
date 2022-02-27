package com.oddinstitute.crossplatformsvgparser

import com.oddinstitute.crossplatformsvgparser.objects.ShapeAttr
import com.oddinstitute.crossplatformsvgparser.to_refactor.Segment
import com.oddinstitute.crossplatformsvgparser.to_refactor.SegmentType

class MyPath2
{
    var pathData = PathData()

    fun makeCurvePath(shapeAttr: ShapeAttr, closed: Boolean, segments: ArrayList<Segment>)
    {
        pathData = PathData()

        pathData.fillRule = shapeAttr.fillType

        for (seg in segments)
        {
//            seg.addToPath(this) // this used to be this.path

            when (seg.type)
            {
                SegmentType.Curve ->
                {
                    seg.o?.let { oo ->
                        seg.i?.let { ii ->
                            this.pathData.pieces.add(PathPiece(MyPathPieceType.CURVE, seg.v, seg.o, seg.i))
                        }
                    }
                }
                SegmentType.Line -> this.pathData.pieces.add(PathPiece(MyPathPieceType.LINE, seg.v))
                SegmentType.Move -> this.pathData.pieces.add(PathPiece(MyPathPieceType.MOVE, seg.v))
            }

        }

        pathData.closed = closed
    }

    fun makeSharpPath(shapeAttr: ShapeAttr, closed: Boolean, pts: ArrayList<MyVector2>)
    {
        pathData = PathData()
        pathData.fillRule = shapeAttr.fillType

        for (p in pts)
        {
            if (pts.indexOf(p) == 0) this.pathData.pieces.add(PathPiece(MyPathPieceType.MOVE, p))
            else this.pathData.pieces.add(PathPiece(MyPathPieceType.LINE, p))
        }

        pathData.closed = closed
    }
}

data class PathPiece (val pathPieceType: MyPathPieceType,
                      val v: MyVector2,
                      val o: MyVector2? = null,
                      val i: MyVector2? = null)

enum class MyPathPieceType {
    MOVE, LINE, CURVE
}

class PathData ()
{
    var pieces = arrayListOf<PathPiece>()
    var closed: Boolean = false
    var fillRule: String? = ""
}


