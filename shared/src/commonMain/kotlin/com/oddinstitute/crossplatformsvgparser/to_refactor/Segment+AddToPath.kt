package com.oddinstitute.crossplatformsvgparser.to_refactor

import com.oddinstitute.crossplatformsvgparser.MyPath


fun Segment.addToPath(myPath: MyPath)
{
    when (this.type)
    {
        SegmentType.Curve -> {
            this.o?.let { oo ->
                this.i?.let { ii ->
                    myPath.cubicTo(oo.x, oo.y, ii.x, ii.y, v.x, v.y)
                }
            }
        }
        SegmentType.Line -> myPath.lineTo(this.v.x, this.v.y)
        SegmentType.Move -> myPath.moveTo(this.v.x, this.v.y)
    }
}