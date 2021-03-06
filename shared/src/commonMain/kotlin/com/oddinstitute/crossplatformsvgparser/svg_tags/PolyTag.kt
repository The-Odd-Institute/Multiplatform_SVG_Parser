package com.oddinstitute.crossplatformsvgparser.svg_tags

import android.graphics.PointF
import com.oddinstitute.crossplatformsvgparser.Segment
import com.oddinstitute.crossplatformsvgparser.SegmentType
import com.oddinstitute.crossplatformsvgparser.SevenPieceArc
import com.oddinstitute.crossplatformsvgparser.objects.Object
import com.oddinstitute.crossplatformsvgparser.objects.PathObj
import com.oddinstitute.crossplatformsvgparser.objects.PolyObj
import com.oddinstitute.crossplatformsvgparser.toSegmentsObjCMethod
import org.xmlpull.v1.XmlPullParser

// if it is polygon, it will be closed
class PolyTag(val parser: XmlPullParser, var closed: Boolean = false) : Tag(parser)
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

    init
    {
        // POLYGON
        parser.getAttributeValue(null, "points")?.let { points = it }

        // LINE
        parser.getAttributeValue(null, "x1")?.let { x1 = it.toFloat() }
        parser.getAttributeValue(null, "y1")?.let { y1 = it.toFloat() }
        parser.getAttributeValue(null, "x2")?.let { x2 = it.toFloat() }
        parser.getAttributeValue(null, "y2")?.let { y2 = it.toFloat() }

        // RECT
        parser.getAttributeValue(null, "x")?.let { x = it.toFloat() }
        parser.getAttributeValue(null, "y")?.let { y = it.toFloat() }
        parser.getAttributeValue(null, "width")?.let { width = it.toFloat() }
        parser.getAttributeValue(null, "height")?.let { height = it.toFloat() }
        parser.getAttributeValue(null, "rx")?.let { rx = it.toFloat() }
        parser.getAttributeValue(null, "ry")?.let { ry = it.toFloat() }
    }


    override fun toObject(): Object?
    {
        // POLYLINE
        points?.let {

            // if it is closed, we consider that
            val polygon = PolyObj(closed)

            // it turns our polylines can have either SPACES or COMMAS to separate pairs
            // for instance 20,20,40,25,60,40 is the same as 20,20 40,25 60,40
            // to eliminate this problem, we replace SPACES with COMMAS

            val pointsComponents = Tag.clean(it)
                    .replace(" ", ",")
                    .split(",")

            // we go through these every 2
            for (i in 0 until pointsComponents.count() step 2)
            {
                val xValue = pointsComponents[i].toFloat()
                val yValue = pointsComponents[i + 1].toFloat()

                val point = PointF(xValue, yValue)

                // at the end, add to the segments
                polygon.pt.add(point)
            }

            return polygon
        }

        // LINE, so it can't be closed, we don't need the "closed"
        x1?.let {
            val polygon = PolyObj()
            polygon.pt.add(PointF(it, y1))
            polygon.pt.add(PointF(x2, y2))

            return polygon
        }

        // RECT
        width?.let { widthIt ->
            if (rx == null && ry == null) // it's sharp
            {
                val polygon = PolyObj(closed)

                polygon.pt.add(PointF(x, y))
                polygon.pt.add(PointF(x + widthIt, y))
                polygon.pt.add(PointF(x + widthIt, y + height))
                polygon.pt.add(PointF(x, y + height))

                return polygon
            }
            else // it's smooth
            {
                val pathObj: PathObj = PathObj(closed)

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

                val moveSeg = Segment(SegmentType.Move, PointF(x, y + radY))
                pathObj.segments.add(moveSeg)

                val topLeftArc = SevenPieceArc(radX, radY, 0f,
                                               largeArcFlag = false,
                                               sweepFlag = true,
                                               x2 = x + radX,
                                               y2 = y)

                val topLeftSegs = topLeftArc.toSegmentsObjCMethod(PointF(x, y + radY))
                pathObj.segments.addAll(topLeftSegs)

                val topLine = Segment(SegmentType.Line)
                topLine.v = PointF(x + widthIt - radY, y)
                pathObj.segments.add(topLine)

                val topRightArc = SevenPieceArc(radX, radY, 0f,
                                                largeArcFlag = false,
                                                sweepFlag = true,
                                                x2 = x + widthIt,
                                                y2 = y + radY)
                val topRightSegs = topRightArc.toSegmentsObjCMethod(PointF(x + widthIt - radX, y))
                pathObj.segments.addAll(topRightSegs)

                val rightLine = Segment(SegmentType.Line)
                rightLine.v = PointF(x + widthIt, y + height - radY)
                pathObj.segments.add(rightLine)

                val bottomRightArc = SevenPieceArc(radX, radY, 0f,
                                                   largeArcFlag = false,
                                                   sweepFlag = true,
                                                   x2 = x + widthIt - radX,
                                                   y2 = y + height)
                val bottomRightSegs =
                    bottomRightArc.toSegmentsObjCMethod(PointF(x + widthIt, y + height - radY))
                pathObj.segments.addAll(bottomRightSegs)


                val bottomLine = Segment(SegmentType.Line)
                bottomLine.v = PointF(x + radX, y + height)
                pathObj.segments.add(bottomLine)

                val bottomLeftArc = SevenPieceArc(radX, radY, 0f,
                                                  largeArcFlag = false,
                                                  sweepFlag = true,
                                                  x2 = x,
                                                  y2 = y + height - radY)
                val bottomLeftSegs = bottomLeftArc.toSegmentsObjCMethod(PointF(x + radX, y + height))
                pathObj.segments.addAll(bottomLeftSegs)


                return pathObj
            }
        }

        return null
    }
}