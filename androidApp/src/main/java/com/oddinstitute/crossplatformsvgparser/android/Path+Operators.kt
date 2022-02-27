package com.oddinstitute.crossplatformsvgparser.android

import android.graphics.Path
import com.oddinstitute.crossplatformsvgparser.MyVector2

fun Path.cubicTo(x: Float, y: Float, ox: Float, oy: Float, ix: Float, iy: Float)
{
    this.cubicTo(ox, oy, ix, iy, x, y)
}

fun Path.lineTo(x: Float, y: Float)
{
    this.lineTo(x, y)
}

fun Path.moveTo(x: Float, y: Float)
{
    this.moveTo(x, y)
}

fun Path.lineToPoint(myVector2: MyVector2) {
    this.lineTo(myVector2.x, myVector2.y)
}

fun Path.moveToPoint(myVector2: MyVector2) {
    this.moveTo(myVector2.x, myVector2.y)
}

fun Path.cubicToCpCpPoint(v: MyVector2, o: MyVector2, i: MyVector2) {
    this.cubicTo(o.x, o.y, i.x, i.y, v.x, v.y)
}