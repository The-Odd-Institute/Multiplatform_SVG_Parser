package com.oddinstitute.crossplatformsvgparser

import com.oddinstitute.crossplatformsvgparser.objects.Shape
import com.oddinstitute.crossplatformsvgparser.to_refactor.Segment

expect class MyPath ()
{
    fun makeCurvePath(shape: Shape, closed: Boolean, segments: ArrayList<Segment>)
    fun makeSharpPath(shape: Shape, closed: Boolean, pts: ArrayList<MyVector2>)

    fun cubicTo(ox: Float, oy: Float, ix: Float, iy: Float, x: Float, y: Float)

    fun lineTo(x: Float, y: Float)
    fun moveTo(x: Float, y: Float)
}