package com.oddinstitute.crossplatformsvgparser

import com.oddinstitute.crossplatformsvgparser.objects.Shape

actual class MyPath actual constructor()
{
    actual fun makeCurvePath(shape: Shape, closed: Boolean, segments: ArrayList<Segment>)
    {

    }
    actual fun makeSharpPath(shape: Shape, closed: Boolean, pts: ArrayList<MyVector2>)
    {

    }
}
