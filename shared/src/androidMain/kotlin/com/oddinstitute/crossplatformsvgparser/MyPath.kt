package com.oddinstitute.crossplatformsvgparser

import android.graphics.Path
import com.oddinstitute.crossplatformsvgparser.objects.Shape
import com.oddinstitute.crossplatformsvgparser.to_refactor.Segment
import com.oddinstitute.crossplatformsvgparser.to_refactor.addToPath

actual class MyPath
{
    var path: Path = Path()


    actual fun makeCurvePath(shape: Shape, closed: Boolean, segments: ArrayList<Segment>)
    {
        this.path = Path()

        // todo
        SvgFillRuleToType(shape.fillType)?.let {
            this.path.fillType = it
        }

        for (seg in segments)
            seg.addToPath(this) // this used to be this.path

        if (closed)
            this.path.close()


    }

    actual fun makeSharpPath(shape: Shape, closed: Boolean, pts: ArrayList<MyVector2>)
    { // todo
        this.path = Path()

        SvgFillRuleToType(shape.fillType)?.let {
            this.path.fillType = it
        }

        for (p in pts)
        {
            if (pts.indexOf(p) == 0) this.path.moveToPoint(p)
            else this.path.lineToPoint(p)
        }

        if (closed) this.path.close()
    }

    actual fun cubicTo(ox: Float, oy: Float, ix: Float, iy: Float, x: Float, y: Float)
    {
        path.cubicTo(ox, oy, ix, iy, x, y)
    }

    actual fun lineTo(x: Float, y: Float)
    {
        path.lineTo(x, y)
    }

    actual fun moveTo(x: Float, y: Float)
    {
        path.moveTo(x, y)
    }

}