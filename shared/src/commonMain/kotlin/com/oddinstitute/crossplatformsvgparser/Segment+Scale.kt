package com.oddinstitute.crossplatformsvgparser



// used for applying SVG Transformations
fun Segment.scale(scaleFactor: MyVector2, pivot: MyVector2)
{
    this.v.scale(scaleFactor, pivot)
    this.o?.scale(scaleFactor, pivot)
    this.i?.scale(scaleFactor, pivot)
}
