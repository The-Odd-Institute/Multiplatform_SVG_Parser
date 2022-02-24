package com.oddinstitute.crossplatformsvgparser.operators


import kotlin.math.PI


//fun PointF.matrixTransform(matrix: SvgMatrixTransform): PointF
//{
//    val x = matrix.a * this.x + matrix.c * this.y + matrix.e
//    val y = matrix.b * this.x + matrix.d * this.y + matrix.f
//
//    return PointF(x, y)
//}

//fun PointF.applyMatrixTransform(matrix: SvgMatrixTransform)
//{
//    val x = matrix.a * this.x + matrix.c * this.y + matrix.e
//    val y = matrix.b * this.x + matrix.d * this.y + matrix.f
//
//    this.x = x
//    this.y = y
//}

//fun PointF.translate(offset: PointF)
//{
//    this.x += offset.x
//    this.y += offset.y
//}

// todo these two exist
//operator fun PointF.times(other: Float) = PointF(this.x * other,
//                                                 this.y * other)



// FIXME - This i different in Moush
//fun PointF.scale(scale: PointF,
//                 pivot: PointF)
//{
//    this.x = (this.x - pivot.x) * scale.x + pivot.x
//    this.y = (this.y - pivot.y) * scale.y + pivot.y
//}



//fun PointF.roundTwoDecimals()
//{
//    this.x = this.x.roundTwoDecimals()
//    this.y = this.y.roundTwoDecimals()
//}


// todo from here down, they all exist
// todo this already exists








//operator fun PointF.plus(other: PointF) = PointF(this.x + other.x,
//                                                 this.y + other.y)

//fun PointF.scale(scaleFactor: Float,
//                 pivot: PointF)
//{
//    this.x = (this.x - pivot.x) * scaleFactor + pivot.x
//    this.y = (this.y - pivot.y) * scaleFactor + pivot.y
//}


//fun PointF.rotate(angle: Float,
//                  pivot: PointF): PointF
//{
//    val x = cos(angle.toRadian()) * (this.x - pivot.x) - sin(
//            angle.toRadian()) * (this.y - pivot.y) + pivot.x
//    val y = sin(angle.toRadian()) * (this.x - pivot.x) + cos(
//            angle.toRadian()) * (this.y - pivot.y) + pivot.y
//
//    return PointF(x.toFloat(),
//                  y.toFloat())
//}

//fun PointF.rotate(angle: Float,
//                  pivot: PointF)
//{
//    val x = (cos(angle.toRadian()) * (this.x - pivot.x) - sin(
//            angle.toRadian()) * (this.y - pivot.y) + pivot.x).toFloat()
//    val y = (sin(angle.toRadian()) * (this.x - pivot.x) + cos(
//            angle.toRadian()) * (this.y - pivot.y) + pivot.y).toFloat()
//
//    // the values of this.x and this.y are needed while calculating.
//    // that's why we cache the x and y
//    this.x = x
//    this.y = y
//}



