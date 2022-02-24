package com.oddinstitute.crossplatformsvgparser

import android.graphics.Path

fun Path.lineToPoint(myVector2: MyVector2) {
    this.lineTo(myVector2.x, myVector2.y)
}

fun Path.moveToPoint(myVector2: MyVector2) {
    this.moveTo(myVector2.x, myVector2.y)
}

fun Path.cubicToCpCpPoint(o: MyVector2, i: MyVector2, v: MyVector2) {
    this.cubicTo(o.x, o.y, i.x, i.y, v.x, v.y)
}



//fun Path.quadToCpPoint(cp1: MyVector2, point: MyVector2) {
//    this.quadTo(cp1.x, cp1.y, point.x, point.y)
//}
//
//fun Path.cubicTo(o: MyVector2, i: MyVector2, v: MyVector2) {
//    this.cubicTo(o.x, o.y, i.x, i.y, v.x, v.y)
//}
//
//fun Path.moveToSeg(segment: Segment) {
//    this.moveTo(segment.v.x, segment.v.y)
//}