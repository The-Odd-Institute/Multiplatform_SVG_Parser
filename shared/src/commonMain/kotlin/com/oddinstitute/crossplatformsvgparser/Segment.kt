package com.oddinstitute.crossplatformsvgparser

import android.graphics.Path
import android.graphics.PointF
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient




@Serializable
class Segment()
{
    companion object
    {
        fun quadToCurve (start: PointF, control: PointF, end: PointF): Segment
        {
            val curve = Segment(SegmentType.Curve)
            // val ox = start.x

            val ox = (start.x + 2f * control.x) / 3f
            val oy = (start.y + 2f * control.y) / 3f

            val ix = (end.x + 2f * control.x) / 3
            val iy = (end.y + 2f * control.y) / 3

            curve.v = end
            curve.i = PointF(ix, iy)
            curve.o = PointF(ox, oy)

            return curve
        }

    }



    lateinit var type: SegmentType

    @Serializable(with = PointFAsStringSerializer::class)
    var v: PointF = PointF()

    @Serializable(with = PointFAsStringSerializer::class)
    var o: PointF? = null // cp1

    @Serializable(with = PointFAsStringSerializer::class)
    var i: PointF? = null // cp2

    constructor(type: SegmentType) : this()
    {
        this.type = type
    }


    // we changed this, made the c1 and c2 with defaults of null
    constructor(t: SegmentType, k: PointF, c1: PointF? = null, c2: PointF? = null) : this(t)
    {
        type = t
        v = k
        o = c1
        i = c2
    }

    @Transient
    var knotDrawn: PointF = PointF()
        /* FIREBASE @Exclude */
        get()
        {
            return field
        }

    @Transient
    var cp1Drawn: PointF? = null
        /* FIREBASE @Exclude */
        get()
        {
            return field
        }

    @Transient
    var cp2Drawn: PointF? = null
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