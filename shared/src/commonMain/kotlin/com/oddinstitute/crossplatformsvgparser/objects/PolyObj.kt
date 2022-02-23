package com.oddinstitute.crossplatformsvgparser.objects

import android.graphics.Path
import android.graphics.PointF
import com.oddinstitute.crossplatformsvgparser.operators.*
import com.oddinstitute.crossplatformsvgparser.svg_transform.SvgTransform
import com.oddinstitute.crossplatformsvgparser.svg_transform.SvgTransformType
import com.oddinstitute.crossplatformsvgparser.temp_draw.lineToPoint
import com.oddinstitute.crossplatformsvgparser.temp_draw.moveToPoint

class PolyObj(val c: Boolean = false) : Object()
{
    val pt: ArrayList<PointF> = arrayListOf()

    override fun roundTwoDecimals()
   {
        for (p in pt)
            p.roundTwoDecimals()
    }

    override fun applySvgViewBox(scaleFactor: Float, offset: PointF)
    {
        this.shape.strokeWidth *= scaleFactor
        this.shape.dashArray?.let { this.shape.dashArray = it * scaleFactor }

        for (p in pt)
        {
            p.offset(-offset.x, -offset.y)
            p.scale(scaleFactor, PointF())
        }
    }

    override fun svgTransform(trans: SvgTransform)
    {
        when (trans.type)
        {
            SvgTransformType.MATRIX -> {
                trans.matrix?.let { matrix ->
                    for (p in pt)
                        p.applyMatrixTransform(matrix)
                }
            }
            SvgTransformType.SCALE -> {
                val scaleFactor = PointF(trans.x, trans.y)

                for (p in pt)
                    p.scale(scaleFactor, PointF())
            }
            SvgTransformType.ROTATE -> {
                val rotatePivot = PointF(trans.cx, trans.cy)
                val angle = trans.angle

                for (p in pt)
                    p.rotate(angle, rotatePivot)
            }
            SvgTransformType.TRANSLATE -> {
                for (p in pt)
                    p.translate(PointF(trans.x, trans.y))
            }
        }
    }

    override fun makePath()
    {
        this.mainPath = Path()

        // todo
        this.mainPath.fillType = this.shape.fillType

        for (p in pt)
        {
            if (pt.indexOf(p) == 0)
                this.mainPath.moveToPoint(p)
            else
                this.mainPath.lineToPoint(p)
        }

        if (this.c)
            mainPath.close()
    }
}