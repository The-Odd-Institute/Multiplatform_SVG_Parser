package com.oddinstitute.crossplatformsvgparser.objects

import com.oddinstitute.crossplatformsvgparser.MyPath2
import com.oddinstitute.crossplatformsvgparser.MyVector2
import com.oddinstitute.crossplatformsvgparser.svg_transform.SvgTransform
import com.oddinstitute.crossplatformsvgparser.svg_transform.SvgTransformType
import com.oddinstitute.crossplatformsvgparser.to_refactor.Segment
import com.oddinstitute.crossplatformsvgparser.to_refactor.rotate
import com.oddinstitute.crossplatformsvgparser.to_refactor.roundTwoDecimals


class Object (val type: ObjectType, val c: Boolean = false)
{
    // this node has many pieces of data
    // not all are applicable to all objects though
    var shapeAttr: ShapeAttr = ShapeAttr()


    // PATHS
    val segments: ArrayList<Segment> = arrayListOf()

    // POLY
    val pt: ArrayList<MyVector2> = arrayListOf()


    // this is not necessarily true for all object types
    var path: MyPath2? = null



//    var shapeNode: NewShapeNode = NewShapeNode()
//
//
//    var closed: Boolean = true
//
//
//    // TODO
//    // these variables are new
//    var strokeLineCap : Paint.Cap = Paint.Cap.ROUND
//    var fillType: Path.FillType = Path.FillType.EVEN_ODD
//    // Clip rule is not currently supported in this parser
//    var clipRule: Path.FillType = Path.FillType.EVEN_ODD
//    var dashArray: Float? = null
//    var strokeLineJoin : Paint.Join = Paint.Join.MITER



    // TODO - All these have to be uncommented

    /*

    @Transient
    var curFrame: Int = 0
        @Exclude get() { return field }

    @Transient
    var artworkLocation: PointF = PointF()
        @Exclude get() { return field }

    @Transient
    var artworkRotation: Float = 0f
        @Exclude get() { return field }

    @Transient
    var artworkScale: PointF = PointF(1f, 1f)
        @Exclude get() { return field }

    @Transient
    var artworkPivot: PointF = PointF()
        @Exclude get() { return field }

    @Transient
    var artworkAlpha: Float = 1f
        @Exclude get() { return field }

    @Transient
    var thumb: Bitmap? = null


    var locked: Boolean = false

    var selected: Boolean = false

    var hidden: Boolean = false

    @Transient
    var hideForLayerThumbs: Boolean = false
        @Exclude get() { return field }


     */
    // thi path is used in the SVG draw app.  We should see if we still need it
//    @Transient var mainPath: Path = Path()
    // @Exclude get() { return field } FIREBASE ONLY


//    @Transient var newGuidesPath: Path? = null
    //  @Exclude get() { return field } FIREBASE ONLY



    /*
    @Transient
    // this is the playable motion that is aggregated for playback time
    var motion: Motion = Motion("Polygon Motion")
        @Exclude get() { return field }

     */

    fun roundTwoDecimals ()
    {
        when (type)
        {
            ObjectType.PATH -> for (seg in segments) seg.roundTwoDecimals()

            ObjectType.POLY -> for (p in pt) p.roundTwoDecimals()
        }
    }

    fun applySvgViewBox(scaleFactor: Float, offset: MyVector2)
    {
        when (type)
        {
            ObjectType.PATH ->
            {
                this.shapeAttr.strokeWidth *= scaleFactor
                // this.shapeAttr.dashArray?.let { this.shapeAttr.dashArray = it * scaleFactor }

                for (seg in segments)
                {
                    seg.v.offset(-offset.x, -offset.y)
                    seg.i?.offset(-offset.x, -offset.y)
                    seg.o?.offset(-offset.x, -offset.y)

                    seg.v.scale(scaleFactor, MyVector2())
                    seg.i?.scale(scaleFactor, MyVector2())
                    seg.o?.scale(scaleFactor, MyVector2())
                }
            }

            ObjectType.POLY ->
            {
                this.shapeAttr.strokeWidth *= scaleFactor
                // this.shapeAttr.dashArray?.let { this.shapeAttr.dashArray = it * scaleFactor }

                for (p in pt)
                {
                    p.offset(-offset.x, -offset.y)
                    p.scale(scaleFactor, MyVector2())
                }
            }
        }



    }


    fun svgTransform(trans: SvgTransform)
    {
        when (type)
        {
            ObjectType.PATH ->
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
                        val scaleFactor = MyVector2(trans.x, trans.y)

                        for (seg in segments) {
                            seg.v.scale(scaleFactor, MyVector2())
                            seg.o?.scale(scaleFactor, MyVector2())
                            seg.i?.scale(scaleFactor, MyVector2())
                        }
                    }
                    SvgTransformType.ROTATE -> {
                        val rotatePivot = MyVector2(trans.cx, trans.cy)
                        val angle = trans.angle

                        for (seg in segments)
                            seg.rotate(angle, rotatePivot)
                    }
                    SvgTransformType.TRANSLATE -> {
                        for (seg in segments) {
                            seg.v.translate(MyVector2(trans.x, trans.y))
                            seg.o?.translate(MyVector2(trans.x, trans.y))
                            seg.i?.translate(MyVector2(trans.x, trans.y))
                        }
                    }
                    else ->
                    {
                        // this is transform NONE
                    }
                }
            }

            ObjectType.POLY ->
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
        }



    }



    // each time, there is a change, this
    // function should happen in the background
    fun makePath()
    {
        when (type)
        {
            ObjectType.PATH ->
            {
                this.path = MyPath2()
                this.path?.makeCurvePath(this.shapeAttr, this.c, this.segments)
//                this.myPath = MyPath()
//                this.myPath.makeCurvePath(this.shapeAttr, this.c, this.segments)
            }

            ObjectType.POLY ->
            {
                this.path = MyPath2()
                this.path?.makeSharpPath(this.shapeAttr, this.c, this.pt)

//                this.myPath = MyPath()
//                this.myPath.makeSharpPath(this.shapeAttr, this.c, this.pt)
            }
        }
    }

//    fun findOrigin(): MyVector2
//    {
        //        // FIXME
        //        // this has to be fixed and go to everything
        //
        //        return super.findOrigin()
        //
        ////        var origX: Float = Float.MAX_VALUE
        ////        var origY: Float = Float.MAX_VALUE
        ////
        ////        for (seg in this.shape.pathValue.segments)
        ////        {
        ////            if (seg.knot.x < origX)
        ////                origX = seg.knot.x
        ////
        ////            if (seg.knot.y < origY)
        ////                origY = seg.knot.y
        ////
        ////
        ////            seg.cp1?.let {
        ////                if (it.x < origX)
        ////                    origX = it.x
        ////
        ////                if (it.y < origY)
        ////                    origY = it.y
        ////            }
        ////
        ////            seg.cp2?.let {
        ////                if (it.x < origX)
        ////                    origX = it.x
        ////
        ////                if (it.y < origY)
        ////                    origY = it.y
        ////            }
        ////        }
        ////
        ////        return PointF(origX, origY)
        //    }
}
