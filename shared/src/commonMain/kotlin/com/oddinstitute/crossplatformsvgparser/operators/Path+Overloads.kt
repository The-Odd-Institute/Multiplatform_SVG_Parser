package com.oddinstitute.crossplatformsvgparser.temp_draw

import android.graphics.Path
import android.graphics.PointF
import com.oddinstitute.crossplatformsvgparser.Segment

//import com.oddinstitute.moush.MyPointF

fun Path.lineToPoint(point: PointF)
{
    this.lineTo(point.x,
                point.y)
}

fun Path.moveToPoint(point: PointF)
{
    this.moveTo(point.x,
        point.y)
}


// FIXME - NEW

fun Path.cubicTo (o: PointF, i: PointF, v: PointF)
{
    this.cubicTo(o.x, o.y, i.x, i.y, v.x, v.y)
}

fun Path.moveToSeg(segment: Segment)
{
    this.moveTo(segment.v.x,
                segment.v.y)
}



fun Path.cubicToCpCpPoint (cp1: PointF, cp2: PointF, point: PointF)
{
    this.cubicTo(cp1.x, cp1.y, cp2.x, cp2.y, point.x, point.y)
}

fun Path.quadToCpPoint (cp1: PointF, point: PointF)
{
    this.quadTo(cp1.x, cp1.y, point.x, point.y)
}