package com.oddinstitute.crossplatformsvgparser.svg_tags

import com.oddinstitute.crossplatformsvgparser.objects.Object
import com.oddinstitute.crossplatformsvgparser.MyVector2
import com.oddinstitute.crossplatformsvgparser.objects.ObjectType
import com.oddinstitute.crossplatformsvgparser.operators.cleanTag
import com.oddinstitute.crossplatformsvgparser.to_refactor.Segment
import com.oddinstitute.crossplatformsvgparser.to_refactor.SegmentType
import com.oddinstitute.crossplatformsvgparser.to_refactor.SevenPieceArc
import com.oddinstitute.crossplatformsvgparser.to_refactor.toSegmentsObjCMethod


class PolyTag (var closed: Boolean = false) : Tag()
{
    // LINE - Here, I assume that if there is an X1, then there definitely is X2, Y1, and Y2
    var x1: Float? = null
    var y1: Float = 0f
    var x2: Float = 0f
    var y2: Float = 0f

    // POLYGON
    var points: String? = null

    // RECT - both x and y can be missing, in that situation, they are 0
    // but width can't be missing
    var x: Float = 0f
    var y: Float = 0f
    var width: Float? = null
    var height: Float = 0f
    var rx: Float? = null
    var ry: Float? = null

    override fun toObject(): Object?
    {
        //        val points = poly.points
        //        val closed = poly.closed
        //        val x = poly.x
        //        val y = poly.y
        //
        //        val x1 = poly.x1
        //        val y1 = poly.y1
        //
        //        val x2 = poly.x2
        //        val y2 = poly.y2
        //
        //        val width = poly.width
        //        val height = poly.height
        //        val rx = poly.rx
        //        val ry = poly.ry


        // POLYLINE
        points?.let {

            // if it is closed, we consider that
            val polygon = Object(ObjectType.POLY, closed)

            // it turns our polylines can have either SPACES or COMMAS to separate pairs
            // for instance 20,20,40,25,60,40 is the same as 20,20 40,25 60,40
            // to eliminate this problem, we replace SPACES with COMMAS

            val pointsComponents = it.cleanTag()
                .replace(" ", ",")
                .split(",")

            // we go through these every 2
            for (i in 0 until pointsComponents.count() step 2)
            {
                val xValue = pointsComponents[i].toFloat()
                val yValue = pointsComponents[i + 1].toFloat()

                val point = MyVector2(xValue, yValue)

                // at the end, add to the segments
                polygon.pt.add(point)
            }

            return polygon
        }

        // LINE, so it can't be closed, we don't need the "closed"
        x1?.let {
            val polygon = Object(ObjectType.POLY)
            polygon.pt.add(MyVector2(it, y1))
            polygon.pt.add(MyVector2(x2, y2))

            return polygon
        }

        // RECT
        width?.let { widthIt ->
            if (rx == null && ry == null) // it's sharp
            {
                val polygon = Object(ObjectType.POLY, closed)

                polygon.pt.add(MyVector2(x, y))
                polygon.pt.add(MyVector2(x + widthIt, y))
                polygon.pt.add(MyVector2(x + widthIt, y + height))
                polygon.pt.add(MyVector2(x, y + height))

                return polygon
            }
            else // it's smooth
            {
                val pathObj = Object(ObjectType.PATH, closed)

                var radX = 0f
                var radY = 0f

                rx?.let {
                    radX = it
                    if (ry == null) radY = it
                }
                ry?.let {
                    radY = it
                    if (rx == null) radX = it
                }

                val moveSeg = Segment(SegmentType.Move, MyVector2(x, y + radY))
                pathObj.segments.add(moveSeg)

                val topLeftArc = SevenPieceArc(radX, radY, 0f,
                                               largeArcFlag = false,
                                               sweepFlag = true,
                                               x2 = x + radX,
                                               y2 = y)

                val topLeftSegs = topLeftArc.toSegmentsObjCMethod(MyVector2(x, y + radY))
                pathObj.segments.addAll(topLeftSegs)

                val topLine = Segment(SegmentType.Line)
                topLine.v = MyVector2(x + widthIt - radY, y)
                pathObj.segments.add(topLine)

                val topRightArc = SevenPieceArc(radX, radY, 0f,
                                                largeArcFlag = false,
                                                sweepFlag = true,
                                                x2 = x + widthIt,
                                                y2 = y + radY)
                val topRightSegs = topRightArc.toSegmentsObjCMethod(MyVector2(x + widthIt - radX, y))
                pathObj.segments.addAll(topRightSegs)

                val rightLine = Segment(SegmentType.Line)
                rightLine.v = MyVector2(x + widthIt, y + height - radY)
                pathObj.segments.add(rightLine)

                val bottomRightArc = SevenPieceArc(radX, radY, 0f,
                                                   largeArcFlag = false,
                                                   sweepFlag = true,
                                                   x2 = x + widthIt - radX,
                                                   y2 = y + height)
                val bottomRightSegs =
                    bottomRightArc.toSegmentsObjCMethod(MyVector2(x + widthIt, y + height - radY))
                pathObj.segments.addAll(bottomRightSegs)


                val bottomLine = Segment(SegmentType.Line)
                bottomLine.v = MyVector2(x + radX, y + height)
                pathObj.segments.add(bottomLine)

                val bottomLeftArc = SevenPieceArc(radX, radY, 0f,
                                                  largeArcFlag = false,
                                                  sweepFlag = true,
                                                  x2 = x,
                                                  y2 = y + height - radY)
                val bottomLeftSegs = bottomLeftArc.toSegmentsObjCMethod(MyVector2(x + radX, y + height))
                pathObj.segments.addAll(bottomLeftSegs)


                return pathObj
            }
        }

        return null
    }
}