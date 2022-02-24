package com.oddinstitute.crossplatformsvgparser.to_refactor

fun Segment.roundTwoDecimals()
{
    this.v.roundTwoDecimals()
    this.i?.roundTwoDecimals()
    this.o?.roundTwoDecimals()
}
