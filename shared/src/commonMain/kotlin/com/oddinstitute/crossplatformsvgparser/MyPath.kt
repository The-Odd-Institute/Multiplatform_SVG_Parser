package com.oddinstitute.crossplatformsvgparser

import com.oddinstitute.crossplatformsvgparser.objects.Shape

expect class MyPath ()
{
    fun makeCurvePath(shape: Shape, closed: Boolean, segments: ArrayList<Segment>)
    fun makeSharpPath(shape: Shape, closed: Boolean, pts: ArrayList<MyVector2>)
}
