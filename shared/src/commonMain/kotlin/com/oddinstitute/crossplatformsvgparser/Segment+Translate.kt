package com.oddinstitute.crossplatformsvgparser


// used for applying SVG Transformations
fun Segment.translate(offset: MyVector2)
{
    this.v.translate(offset)
    this.o?.translate(offset)
    this.i?.translate(offset)
}