package com.oddinstitute.crossplatformsvgparser

import com.oddinstitute.crossplatformsvgparser.to_refactor.Segment
import com.oddinstitute.crossplatformsvgparser.objects.Shape

actual class MyPath actual constructor()
{
    actual fun makeCurvePath(shape: Shape, closed: Boolean, segments: ArrayList<Segment>)
    {

    }
    actual fun makeSharpPath(shape: Shape, closed: Boolean, pts: ArrayList<MyVector2>)
    {

    }

    actual fun cubicTo(ox: Float, oy: Float, ix: Float, iy: Float, x: Float, y: Float)
    {
    }

    actual fun lineTo(x: Float, y: Float)
    {
    }

    actual fun moveTo(x: Float, y: Float)
    {
    }
}