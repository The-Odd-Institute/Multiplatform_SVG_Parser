package com.oddinstitute.crossplatformsvgparser

import com.oddinstitute.crossplatformsvgparser.operators.roundTwoDecimals

fun Segment.roundTwoDecimals()
{
    this.v.roundTwoDecimals()
    this.i?.roundTwoDecimals()
    this.o?.roundTwoDecimals()
}
