package com.oddinstitute.crossplatformsvgparser

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient




@Serializable
class Segment()
{
    companion object
    {
        fun quadToCurve (start: MyVector2, control: MyVector2, end: MyVector2): Segment
        {
            val curve = Segment(SegmentType.Curve)
            // val ox = start.x

            val ox = (start.x + 2f * control.x) / 3f
            val oy = (start.y + 2f * control.y) / 3f

            val ix = (end.x + 2f * control.x) / 3
            val iy = (end.y + 2f * control.y) / 3

            curve.v = end
            curve.i = MyVector2(ix, iy)
            curve.o = MyVector2(ox, oy)

            return curve
        }

    }



    lateinit var type: SegmentType

    @Serializable(with = com.oddinstitute.crossplatformsvgparser.MyVector2AsStringSerializer::class)
    var v: MyVector2 = MyVector2()

    @Serializable(with = com.oddinstitute.crossplatformsvgparser.MyVector2AsStringSerializer::class)
    var o: MyVector2? = null // cp1

    @Serializable(with = com.oddinstitute.crossplatformsvgparser.MyVector2AsStringSerializer::class)
    var i: MyVector2? = null // cp2

    constructor(type: SegmentType) : this()
    {
        this.type = type
    }


    // we changed this, made the c1 and c2 with defaults of null
    constructor(t: SegmentType, k: MyVector2, c1: MyVector2? = null, c2: MyVector2? = null) : this(t)
    {
        type = t
        v = k
        o = c1
        i = c2
    }

    @Transient
    var knotDrawn: MyVector2 = MyVector2()
        /* FIREBASE @Exclude */
        get()
        {
            return field
        }

    @Transient
    var cp1Drawn: MyVector2? = null
        /* FIREBASE @Exclude */
        get()
        {
            return field
        }

    @Transient
    var cp2Drawn: MyVector2? = null
        /* FIREBASE @Exclude */
        get()
        {
            return field
        }


    @Transient
    var knotPath: CornerPath? = null
        /* FIREBASE @Exclude */
        get()
        {
            return field
        }

    @Transient
    var cp1Path: CornerPath? = null
        /* FIREBASE @Exclude */
        get()
        {
            return field
        }

    @Transient
    var cp2Path: CornerPath? = null
        /* FIREBASE @Exclude */
        get()
        {
            return field
        }

    @Transient
    var selected: Boolean = false
        /* FIREBASE @Exclude */
        get()
        {
            return field
        }
}

fun Segment.rotate(angle: Float, pivot: MyVector2)
{
    this.v.rotate(angle, pivot)
    this.o?.rotate(angle, pivot)
    this.i?.rotate(angle, pivot)
}