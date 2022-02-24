package com.oddinstitute.crossplatformsvgparser.objects

import com.oddinstitute.crossplatformsvgparser.MyPath
import com.oddinstitute.crossplatformsvgparser.MyVector2
import com.oddinstitute.crossplatformsvgparser.svg_transform.SvgTransform

open class Object
{
    // this node has many pieces of data
    // not all are applicable to all objects though
    var shape: Shape = Shape()


    // this is not necessarily true for all object types
    var myPath: MyPath = MyPath()



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

    open fun roundTwoDecimals ()
    {

    }

    open fun applySvgViewBox(scaleFactor: Float, offset: MyVector2)
    {

    }


    open fun svgTransform(trans: SvgTransform)
    {
//        if (trans.type == SvgTransformType.MATRIX)
//        {
//            trans.matrix?.let { matrix ->
//                val x = sign(matrix.a) * sqrt(matrix.a.pow(2f) + matrix.c.pow(2f))
//                val y = sign(matrix.d) * sqrt(matrix.b.pow(2f) + matrix.d.pow(2f))
//
//                this.shape.strokeWidth *= ((x + y) / 2f)
//            }
//        }
//        else
//            if (trans.type == SvgTransformType.SCALE)
//        {
//            val scaleFactor = PointF(trans.x,
//                                     trans.y)
//            this.shape.strokeWidth *= ((scaleFactor.x + scaleFactor.y ) / 2f)
//        }

    }

    open fun makePath()
    {

    }

    open fun findOrigin(): MyVector2
    {
        return MyVector2()

    }


}
