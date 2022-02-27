package com.oddinstitute.crossplatformsvgparser.android

import android.graphics.Path
import com.oddinstitute.crossplatformsvgparser.MyPath2
import com.oddinstitute.crossplatformsvgparser.MyPathPieceType


fun MyPath2.makeCgPath () : Path
{
    val path = Path ()

    for (piece in this.pathData.pieces)
    {
        when (piece.pathPieceType)
        {
            MyPathPieceType.MOVE -> path.moveToPoint(piece.v)
            MyPathPieceType.CURVE -> {
                piece.o?.let { o ->
                    piece.i?.let { i ->
                        path.cubicToCpCpPoint(piece.v, o, i)
                    }
                }
            }
            MyPathPieceType.LINE -> path.lineToPoint(piece.v)
        }
    }

    if (this.pathData.closed)
        path.close()

    this.pathData.fillRule?.let {
        path.fillType = Utils.svgFillRuleToType(it)
    }

    return path
}