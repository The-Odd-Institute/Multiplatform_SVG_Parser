package com.oddinstitute.crossplatformsvgparser.to_refactor

import com.oddinstitute.crossplatformsvgparser.MyVector2


// used for applying SVG Transformations
fun Segment.translate(offset: MyVector2)
{
    this.v.translate(offset)
    this.o?.translate(offset)
    this.i?.translate(offset)
}