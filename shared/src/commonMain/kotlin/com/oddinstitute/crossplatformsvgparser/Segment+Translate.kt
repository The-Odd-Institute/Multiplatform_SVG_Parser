package com.oddinstitute.crossplatformsvgparser

import android.graphics.PointF
import com.oddinstitute.crossplatformsvgparser.operators.translate

// used for applying SVG Transformations
fun Segment.translate(offset: PointF)
{
    this.v.translate(offset)
    this.o?.translate(offset)
    this.i?.translate(offset)
}