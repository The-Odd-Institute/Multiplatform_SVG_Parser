package com.oddinstitute.crossplatformsvgparser.operators

import android.graphics.Path
import com.oddinstitute.crossplatformsvgparser.MyVector2
import com.oddinstitute.crossplatformsvgparser.Segment

//import com.oddinstitute.moush.MyPointF

fun Path.lineToPoint(point: MyVector2)
{
    this.lineTo(point.x,
                point.y)
}

fun Path.moveToPoint(point: MyVector2)
{
    this.moveTo(point.x,
        point.y)
}


// FIXME - NEW

fun Path.cubicTo (o: MyVector2, i: MyVector2, v: MyVector2)
{
    this.cubicTo(o.x, o.y, i.x, i.y, v.x, v.y)
}

fun Path.moveToSeg(segment: Segment)
{
    this.moveTo(segment.v.x,
                segment.v.y)
}



fun Path.cubicToCpCpPoint (cp1: MyVector2, cp2: MyVector2, point: MyVector2)
{
    this.cubicTo(cp1.x, cp1.y, cp2.x, cp2.y, point.x, point.y)
}

fun Path.quadToCpPoint (cp1: MyVector2, point: MyVector2)
{
    this.quadTo(cp1.x, cp1.y, point.x, point.y)
}