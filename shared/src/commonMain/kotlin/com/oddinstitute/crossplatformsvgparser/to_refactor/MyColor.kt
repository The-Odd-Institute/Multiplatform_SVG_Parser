package com.oddinstitute.crossplatformsvgparser.to_refactor

import com.oddinstitute.crossplatformsvgparser.operators.roundTwoDecimals


class MyColor ()
{
    // default color is fully transparent white?
    var r: Float = 1f
    var g: Float = 1f
    var b: Float = 1f
    var a: Float = 1f

    constructor(r: Float, g: Float, b: Float, a: Float? = 1f) : this()
    {
        this.r = r
        this.g = g
        this.b = b

        a?.let {
            this.a = it
        }
    }

    fun roundTwoDecimals(): MyColor
    {
        val r = this.r.roundTwoDecimals()
        val g = this.g.roundTwoDecimals()
        val b = this.b.roundTwoDecimals()
        val a = this.a.roundTwoDecimals()

        return MyColor(r, g, b, a)
    }
}