package com.oddinstitute.crossplatformsvgparser

import android.graphics.Path
import com.oddinstitute.crossplatformsvgparser.objects.Shape
import com.oddinstitute.crossplatformsvgparser.operators.lineToPoint
import com.oddinstitute.crossplatformsvgparser.operators.moveToPoint


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

        if (closed) path.close()

        for (seg in segments) seg.addToPath(this.path)
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
}
