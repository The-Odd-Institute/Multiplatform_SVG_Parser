package com.oddinstitute.crossplatformsvgparser

import android.graphics.Path

fun Segment.addToPath(path: Path)
{
    when (this.type)
    {
        SegmentType.Curve -> {
            this.o?.let { oo ->
                this.i?.let { ii ->
                    path.cubicTo(oo.x, oo.y, ii.x, ii.y, v.x, v.y)
                }
            }
        }
        SegmentType.Line -> path.lineTo(this.v.x, this.v.y)
        SegmentType.Move -> path.moveTo(this.v.x, this.v.y)
    }
}