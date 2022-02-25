package com.oddinstitute.crossplatformsvgparser

import com.oddinstitute.crossplatformsvgparser.objects.ShapeAttr
import com.oddinstitute.crossplatformsvgparser.to_refactor.Segment

expect class MyPath ()
{
    fun makeCurvePath(shapeAttr: ShapeAttr, closed: Boolean, segments: ArrayList<Segment>)
    fun makeSharpPath(shapeAttr: ShapeAttr, closed: Boolean, pts: ArrayList<MyVector2>)

    fun cubicTo(ox: Float, oy: Float, ix: Float, iy: Float, x: Float, y: Float)

    fun lineTo(x: Float, y: Float)
    fun moveTo(x: Float, y: Float)
}