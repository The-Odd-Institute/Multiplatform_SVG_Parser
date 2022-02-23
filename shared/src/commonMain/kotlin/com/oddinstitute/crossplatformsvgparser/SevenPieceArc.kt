package com.oddinstitute.crossplatformsvgparser

import android.graphics.Matrix
import android.graphics.PointF
import kotlin.math.*

// rx ry x-axis-rotation large-arc-flag sweep-flag x y
data class SevenPieceArc(var rx: Float,
                          var ry: Float,
                          val xAxisRotation: Float,
                          val largeArcFlag: Boolean,
                          val sweepFlag: Boolean,
                          val x2: Float,
                          val y2: Float)

fun SevenPieceArc.toSegmentsObjCMethod(curPoint: PointF): ArrayList<Segment>
{
    val outSegments = ArrayList<Segment>()
//
//        if (prevSegment.type == PathType.Arc)
//        {
//            x2 += curPoint.x
//            y2 += curPoint.y
//        }

    val x0 = curPoint.x
    val y0 = curPoint.y
    val xAxisRotation = this.xAxisRotation

    val x = this.x2
    val y = this.y2

    var rx = this.rx
    var ry = this.ry

    val largeArcFlag = this.largeArcFlag
    val sweepFlag = this.sweepFlag


    // Convert angle from degrees to radians
    val TwoPI: Float = (PI * 2.0).toFloat()
    val sinAngle: Float = sin(xAxisRotation * TwoPI / 360)
    val cosAngle: Float = cos(xAxisRotation * TwoPI / 360)


    val x1Prime: Float = cosAngle * (x0 - x) / 2 + sinAngle * (y0 - y) / 2
    val y1Prime: Float = -sinAngle * (x0 - x) / 2 + cosAngle * (y0 - y) / 2

    if (x1Prime == 0.0f && y1Prime == 0.0f)
        return outSegments

    // Sign of the radii is ignored (behaviour specified by the spec)
    rx = abs(rx)
    ry = abs(ry)


    val lambda: Float = x1Prime.pow(2) / rx.pow(2) + y1Prime.pow(2) / ry.pow(2)

    if (lambda > 1)
    {
        rx *= sqrt(lambda)
        ry *= sqrt(lambda)
    }

    // Step 2: Compute (cx′, cy′)

    val rxSq: Float = rx.pow(2)
    val rySq: Float = ry.pow(2)
    val x1PrimeSq: Float = x1Prime.pow(2)
    val y1PrimeSq: Float = y1Prime.pow(2)

    var radicant: Float = (rxSq * rySq) - (rxSq * y1PrimeSq) - (rySq * x1PrimeSq)

    if (radicant < 0)
        radicant = 0f

    radicant /= (rxSq * y1PrimeSq) + (rySq * x1PrimeSq)

    // where the + sign is chosen if fA ≠ fS, and the − sign is chosen if fA = fS.
    radicant = sqrt(radicant) * (if (largeArcFlag == sweepFlag) -1; else 1)

    val xCenterPrime: Float = radicant * rx / ry * y1Prime
    val yCenterPrime: Float = radicant * -ry / rx * x1Prime


    // Step 3: Compute (cx, cy) from (cx′, cy′)
    val centerX: Float = cosAngle * xCenterPrime - sinAngle * yCenterPrime + (x0 + x) / 2
    val centerY: Float = sinAngle * xCenterPrime + cosAngle * yCenterPrime + (y0 + y) / 2


    // Step 4: Compute θ1 and Δθ
    val vec1_x: Float = (x1Prime - xCenterPrime) / rx
    val vec1_y: Float = (y1Prime - yCenterPrime) / ry

    val vec2_x: Float = (-x1Prime - xCenterPrime) / rx
    val vec2_y: Float = (-y1Prime - yCenterPrime) / ry

    var ang1: Float = vectorAngle(1f, 0f, vec1_x, vec1_y)
    var ang2: Float = vectorAngle(vec1_x, vec1_y, vec2_x, vec2_y)

    if (!sweepFlag && ang2 > 0)
        ang2 -= TwoPI // mod 360

    if (sweepFlag && ang2 < 0)
        ang2 += TwoPI // mod 360

    val segments: Int = maxOf(ceil(abs(ang2) / (TwoPI / 4.0)).toInt(), 1)

    ang2 /= segments


    for (i in 0 until segments)
    {
        val a: Float = 4.0f / 3.0f * tan(ang2 / 4.0f);

        val x1: Float = cos(ang1)
        val y1: Float = sin(ang1)
        val x2: Float = cos(ang1 + ang2)
        val y2: Float = sin(ang1 + ang2)

        val cp1: PointF =
            mapToEllipse(x1 - y1 * a, y1 + x1 * a, rx, ry, cosAngle, sinAngle, centerX, centerY)
        val cp2: PointF =
            mapToEllipse(x2 + y2 * a, y2 - x2 * a, rx, ry, cosAngle, sinAngle, centerX, centerY)
        val knot: PointF = mapToEllipse(x2, y2, rx, ry, cosAngle, sinAngle, centerX, centerY)

        val arcSegment = Segment(SegmentType.Curve)

        arcSegment.v = knot
        arcSegment.o = cp1
        arcSegment.i = cp2

        outSegments.add(arcSegment)

//            CGPathAddCurveToPoint(_path, NULL, p1.x, p1.y, p2.x, p2.y, p.x, p.y);
//            _lastControlPoint = p2;

        ang1 += ang2;
    }

    return outSegments
}


fun SevenPieceArc.vectorAngle(ux: Float, uy: Float, vx: Float, vy: Float): Float
{
    val sign: Float = if (ux * vy - uy * vx < 0) -1f; else 1f
    val umag: Float = sqrt(ux * ux + uy * uy)
    val vmag: Float = sqrt(ux * ux + uy * uy)
    val dot: Float = ux * vx + uy * vy

    var div = dot / (umag * vmag)

    if (div > 1)
        div = 1f


    if (div < -1)
        div = -1f


    return sign * acos(div)
}

fun SevenPieceArc.mapToEllipse(x: Float,
                               y: Float,
                               rx: Float,
                               ry: Float,
                               cosPhi: Float,
                               sinPhi: Float,
                               centerX: Float,
                               centerY: Float): PointF
{
    val xx = x * rx
    val yy = y * ry

    val xp: Float = cosPhi * xx - sinPhi * yy
    val yp: Float = sinPhi * xx + cosPhi * yy

    return PointF(xp + centerX, yp + centerY)
}

fun SevenPieceArc.toSegmentsJavaMethod(curPoint: PointF): ArrayList<Segment>
{
    val segmentsArr: ArrayList<Segment> = arrayListOf()

    val x0 = curPoint.x
    val y0 = curPoint.y
    val xAxisRotation = this.xAxisRotation

    val x = this.x2
    val y = this.y2

    var rx = this.rx
    var ry = this.ry

    val largeArcFlag = this.largeArcFlag
    val sweepFlag = this.sweepFlag


    if (x0 == x && y0 == y)
    {
        // If the endpoints (x, y) and (x0, y0) are identical, then this
        // is equivalent to omitting the elliptical arc segment entirely.
        // (behaviour specified by the spec)
        return segmentsArr
    }

    // Handle degenerate case (behaviour specified by the spec)
    if (rx == 0f || ry == 0f)
    {
        val lineSeg = Segment(SegmentType.Line, PointF(x, y))

        segmentsArr.add(lineSeg)
        return segmentsArr
    }

    // Sign of the radii is ignored (behaviour specified by the spec)
    rx = abs(rx)
    ry = abs(ry)

    // Convert angle from degrees to radians

    //fun Degree.toRadian(): Radian = this / 180 x Math.PI

    // used to be
//    val angleRad = Math.toRadians(xAxisRotation % 360.0) / 180.0 * PI
    val angleRad = (xAxisRotation % 360.0) / 180.0 * PI
    val cosAngle = cos(angleRad)
    val sinAngle = sin(angleRad)

    // We simplify the calculations by transforming the arc so that the origin is at the
    // midpoint calculated above followed by a rotation to line up the coordinate axes
    // with the axes of the ellipse.

    // Compute the midpoint of the line between the current and the end point
    val dx2 = (x0 - x) / 2.0
    val dy2 = (y0 - y) / 2.0

    // Step 1 : Compute (x1', y1')
    // x1,y1 is the midpoint vector rotated to take the arc's angle out of consideration
    val x1 = cosAngle * dx2 + sinAngle * dy2
    val y1 = -sinAngle * dx2 + cosAngle * dy2

    var rxSqr = (rx * rx).toDouble()
    var rySqr = (ry * ry).toDouble()
    val x1Sqr = x1 * x1
    val y1Sqr = y1 * y1

    // Check that radii are large enough.
    // If they are not, the spec says to scale them up so they are.
    // This is to compensate for potential rounding errors/differences between SVG implementations.
    val radiiCheck = x1Sqr / rxSqr + y1Sqr / rySqr
    if (radiiCheck > 0.99999)
    {
        val radiiScale = sqrt(radiiCheck) * 1.00001
        rx = (radiiScale * rx).toFloat()
        ry = (radiiScale * ry).toFloat()
        rxSqr = (rx * rx).toDouble()
        rySqr = (ry * ry).toDouble()
    }

    // Step 2 : Compute (cx1, cy1) - the transformed centre point
    var sign: Double = if (largeArcFlag == sweepFlag) -1.0 else 1.0
    var sq = (rxSqr * rySqr - rxSqr * y1Sqr - rySqr * x1Sqr) / (rxSqr * y1Sqr + rySqr * x1Sqr)
    sq = if (sq < 0) 0.0 else sq
    val coef = sign * sqrt(sq)
    val cx1 = coef * (rx * y1 / ry)
    val cy1 = coef * -(ry * x1 / rx)

    // Step 3 : Compute (cx, cy) from (cx1, cy1)
    val sx2 = (x0 + x) / 2.0
    val sy2 = (y0 + y) / 2.0
    val cx = sx2 + (cosAngle * cx1 - sinAngle * cy1)
    val cy = sy2 + (sinAngle * cx1 + cosAngle * cy1)

    // Step 4 : Compute the angleStart (angle1) and the angleExtent (dangle)
    val ux = (x1 - cx1) / rx
    val uy = (y1 - cy1) / ry
    val vx = (-x1 - cx1) / rx
    val vy = (-y1 - cy1) / ry

    // Angle betwen two vectors is +/- acos( u.v / len(u) * len(v))
    // Where '.' is the dot product. And +/- is calculated from the sign of the cross product (u x v)
    val TWO_PI = PI * 2.0

    // Compute the start angle
    // The angle between (ux,uy) and the 0deg angle (1,0)
    var n: Double = sqrt(ux * ux + uy * uy)
    var p: Double = ux
    sign = if (uy < 0) -1.0 else 1.0

    var angleStart: Double =
        sign * acos(p / n) // No need for checkedArcCos() here. (p >= n) should always be true.

    // Compute the angle extent
    n = sqrt((ux * ux + uy * uy) * (vx * vx + vy * vy))
    p = ux * vx + uy * vy
    sign = if (ux * vy - uy * vx < 0) -1.0 else 1.0


// Check input to Math.acos() in case rounding or other errors result in a val < -1 or > +1.
// For example, see the possible KitKat JIT error described in issue #62.
    val arcCos: Double = if (p / n < -1.0) PI else if (p / n > 1.0) 0.0 else acos(p / n)


    var angleExtent: Double = sign * arcCos

    // Catch angleExtents of 0, which will cause problems later in arcToBeziers
    if (angleExtent == 0.0)
    {
        val lineSeg = Segment(SegmentType.Line, PointF(x, y))

        segmentsArr.add(lineSeg)
        return segmentsArr
    }
    if (!sweepFlag && angleExtent > 0)
    {
        angleExtent -= TWO_PI
    }
    else if (sweepFlag && angleExtent < 0)
    {
        angleExtent += TWO_PI
    }
    angleExtent %= TWO_PI
    angleStart %= TWO_PI

    // Many elliptical arc implementations including the Java2D and Android ones, only
    // support arcs that are axis aligned.  Therefore we need to substitute the arc
    // with bezier curves.  The following method call will generate the beziers for
    // a unit circle that covers the arc angles we want.
    val bezierPoints: FloatArray = arcToBeziers(angleStart, angleExtent)

    // Calculate a transformation matrix that will move and scale these bezier points to the correct location.
    val m = Matrix()
    m.postScale(rx, ry)
    m.postRotate(xAxisRotation) //todo is this correct?
    m.postTranslate(cx.toFloat(), cy.toFloat())
    m.mapPoints(bezierPoints)

    // The last point in the bezier set should match exactly the last coord pair in the arc (ie: x,y). But
    // considering all the mathematical manipulation we have been doing, it is bound to be off by a tiny
    // fraction. Experiments show that it can be up to around 0.00002.  So why don't we just set it to
    // exactly what it ought to be.
    bezierPoints[bezierPoints.size - 2] = x
    bezierPoints[bezierPoints.size - 1] = y

    // Final step is to add the bezier curves to the path
    var i = 0
    while (i < bezierPoints.size)
    {
        // we are here

        val cp1 = PointF(bezierPoints[i], bezierPoints[i + 1])
        val cp2 = PointF(bezierPoints[i + 2], bezierPoints[i + 3])
        val knot = PointF(bezierPoints[i + 4], bezierPoints[i + 5])
        val curveSeg = Segment(SegmentType.Curve, knot, cp1, cp2)

        segmentsArr.add(curveSeg)
        i += 6
    }
    return segmentsArr
}

fun SevenPieceArc.arcToBeziers(angleStart: Double, angleExtent: Double): FloatArray
{
    val numPieces = ceil(abs(angleExtent) / (PI * 2.0)).toInt() // (angleExtent / 90deg)
    val angleIncrement = angleExtent / numPieces

    // The length of each control point vector is given by the following formula.
    val controlLength = 4.0 / 3.0 * sin(angleIncrement / 2.0) / (1.0 + cos(angleIncrement / 2.0))

    val coords = FloatArray(numPieces * 6)


    var pos = 0
    for (i in 0 until numPieces)
    {
        var angle = angleStart + i * angleIncrement

        // Calculate the control vector at this angle
        var dx = cos(angle)
        var dy = sin(angle)

        // First control point
        coords[pos++] = (dx - controlLength * dy).toFloat()
        coords[pos++] = (dy + controlLength * dx).toFloat()

        // Second control point
        angle += angleIncrement
        dx = cos(angle)
        dy = sin(angle)
        coords[pos++] = (dx + controlLength * dy).toFloat()
        coords[pos++] = (dy - controlLength * dx).toFloat()

        // Endpoint of bezier
        coords[pos++] = dx.toFloat()
        coords[pos++] = dy.toFloat()
    }
    return coords
}