package com.oddinstitute.crossplatformsvgparser.objects

import com.oddinstitute.crossplatformsvgparser.MyPath
import com.oddinstitute.crossplatformsvgparser.MyVector2
import com.oddinstitute.crossplatformsvgparser.operators.*
import com.oddinstitute.crossplatformsvgparser.svg_transform.SvgTransform
import com.oddinstitute.crossplatformsvgparser.svg_transform.SvgTransformType

class PolyObj(val c: Boolean = false) : Object()
{
    val pt: ArrayList<MyVector2> = arrayListOf()

    override fun roundTwoDecimals()
   {
        for (p in pt)
            p.roundTwoDecimals()
    }

    override fun applySvgViewBox(scaleFactor: Float, offset: MyVector2)
    {
        this.shapeAttr.strokeWidth *= scaleFactor
        // this.shapeAttr.dashArray?.let { this.shapeAttr.dashArray = it * scaleFactor }

        for (p in pt)
        {
            p.offset(-offset.x, -offset.y)
            p.scale(scaleFactor, MyVector2())
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
                val scaleFactor = MyVector2(trans.x, trans.y)

                for (p in pt)
                    p.scale(scaleFactor, MyVector2())
            }
            SvgTransformType.ROTATE -> {
                val rotatePivot = MyVector2(trans.cx, trans.cy)
                val angle = trans.angle

                for (p in pt)
                    p.rotate(angle, rotatePivot)
            }
            SvgTransformType.TRANSLATE -> {
                for (p in pt)
                    p.translate(MyVector2(trans.x, trans.y))
            }
        }
    }

    override fun makePath()
    {
        this.myPath = MyPath()
        this.myPath.makeSharpPath(this.shapeAttr, this.c, this.pt)

//        // todo
//        this.mainPath.fillType = this.shape.fillType
//
//        for (p in pt)
//        {
//            if (pt.indexOf(p) == 0)
//                this.mainPath.moveToPoint(p)
//            else
//                this.mainPath.lineToPoint(p)
//        }
//
//        if (this.c)
//            mainPath.close()
    }
}