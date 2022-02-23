package com.oddinstitute.crossplatformsvgparser.objects

import android.graphics.Path
import android.graphics.PointF
import com.oddinstitute.crossplatformsvgparser.Segment
import com.oddinstitute.crossplatformsvgparser.addToPath
import com.oddinstitute.crossplatformsvgparser.operators.*
import com.oddinstitute.crossplatformsvgparser.roundTwoDecimals
import com.oddinstitute.crossplatformsvgparser.svg_transform.SvgTransform
import com.oddinstitute.crossplatformsvgparser.svg_transform.SvgTransformType

class PathObj(val c: Boolean = false) : Object()
{
    val segments: ArrayList<Segment> = arrayListOf()

    // each object type needs its own override
    override fun roundTwoDecimals()
    {
        for (seg in segments)
            seg.roundTwoDecimals()
    }

    override fun applySvgViewBox(scaleFactor: Float, offset: PointF)
    {
        this.shape.strokeWidth *= scaleFactor
        this.shape.dashArray?.let { this.shape.dashArray = it * scaleFactor }

        for (seg in segments)
        {
            seg.v.offset(-offset.x, -offset.y)
            seg.i?.offset(-offset.x, -offset.y)
            seg.o?.offset(-offset.x, -offset.y)

            seg.v.scale(scaleFactor, PointF())
            seg.i?.scale(scaleFactor, PointF())
            seg.o?.scale(scaleFactor, PointF())
        }
    }

    override fun svgTransform(trans: SvgTransform)
    {
        when (trans.type)
        {
            SvgTransformType.MATRIX -> {
                trans.matrix?.let { matrix ->
                    for (seg in segments) {
                        seg.v.applyMatrixTransform(matrix)
                        seg.o?.applyMatrixTransform(matrix)
                        seg.i?.applyMatrixTransform(matrix)
                    }
                }
            }
            SvgTransformType.SCALE -> {
                val scaleFactor = PointF(trans.x, trans.y)

                for (seg in segments) {
                    seg.v.scale(scaleFactor, PointF())
                    seg.o?.scale(scaleFactor, PointF())
                    seg.i?.scale(scaleFactor, PointF())
                }
            }
            SvgTransformType.ROTATE -> {
                val rotatePivot = PointF(trans.cx, trans.cy)
                val angle = trans.angle

                for (seg in segments)
                    seg.rotate(angle, rotatePivot)
            }
            SvgTransformType.TRANSLATE -> {
                for (seg in segments) {
                    seg.v.translate(PointF(trans.x, trans.y))
                    seg.o?.translate(PointF(trans.x, trans.y))
                    seg.i?.translate(PointF(trans.x, trans.y))
                }
            }
        }
    }

    override fun makePath()
    {
        this.mainPath = Path()

        // todo
        this.mainPath.fillType = this.shape.fillType

        if (this.c)
            mainPath.close()

        for (seg in segments)
            seg.addToPath(this.mainPath)
    }


    override fun findOrigin(): PointF
    {
        // FIXME
        // this has to be fixed and go to everything

        return super.findOrigin()

//        var origX: Float = Float.MAX_VALUE
//        var origY: Float = Float.MAX_VALUE
//
//        for (seg in this.shape.pathValue.segments)
//        {
//            if (seg.knot.x < origX)
//                origX = seg.knot.x
//
//            if (seg.knot.y < origY)
//                origY = seg.knot.y
//
//
//            seg.cp1?.let {
//                if (it.x < origX)
//                    origX = it.x
//
//                if (it.y < origY)
//                    origY = it.y
//            }
//
//            seg.cp2?.let {
//                if (it.x < origX)
//                    origX = it.x
//
//                if (it.y < origY)
//                    origY = it.y
//            }
//        }
//
//        return PointF(origX, origY)
    }

}