package com.oddinstitute.crossplatformsvgparser

import android.graphics.Path
import com.oddinstitute.crossplatformsvgparser.objects.ShapeAttr
import com.oddinstitute.crossplatformsvgparser.to_refactor.Segment

//
//actual class MyPath
//{
//    var path: Path = Path()
//
//    actual fun makeCurvePath(shapeAttr: ShapeAttr, closed: Boolean, segments: ArrayList<Segment>)
//    {
//        this.path = Path()
//
//        // todo
//        this.path.fillType  = SvgFillRuleToType(shapeAttr.fillType)
//
//        for (seg in segments)
//            seg.addToPath(this) // this used to be this.path
//
//        if (closed)
//            this.path.close()
//    }
//
//    actual fun makeSharpPath(shapeAttr: ShapeAttr, closed: Boolean, pts: ArrayList<MyVector2>)
//    { // todo
//        this.path = Path()
//
//        this.path.fillType = SvgFillRuleToType(shapeAttr.fillType)
//
//        for (p in pts)
//        {
//            if (pts.indexOf(p) == 0) this.path.moveToPoint(p)
//            else this.path.lineToPoint(p)
//        }
//
//        if (closed) this.path.close()
//    }
//
//    actual fun cubicTo(ox: Float, oy: Float, ix: Float, iy: Float, x: Float, y: Float)
//    {
//        path.cubicTo(ox, oy, ix, iy, x, y)
//    }
//
//    actual fun lineTo(x: Float, y: Float)
//    {
//        path.lineTo(x, y)
//    }
//
//    actual fun moveTo(x: Float, y: Float)
//    {
//        path.moveTo(x, y)
//    }
//}