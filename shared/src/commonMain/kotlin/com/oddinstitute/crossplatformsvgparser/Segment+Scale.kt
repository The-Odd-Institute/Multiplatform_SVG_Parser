package com.oddinstitute.crossplatformsvgparser

import android.graphics.PointF
import com.oddinstitute.crossplatformsvgparser.operators.scale


// used for applying SVG Transformations
fun Segment.scale(scaleFactor: PointF, pivot: PointF)
{
    this.v.scale(scaleFactor, pivot)
    this.o?.scale(scaleFactor, pivot)
    this.i?.scale(scaleFactor, pivot)
}
