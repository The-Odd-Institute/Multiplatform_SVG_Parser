package com.oddinstitute.crossplatformsvgparser.svg_tags


import org.xmlpull.v1.XmlPullParser

class TextTag(val parser: XmlPullParser) : Tag(parser)
{
    // TEXT
    var x = 0f
    var y = 0f

    init
    {
        // TEXT
        parser.getAttributeValue(null, "x")?.let { x = it.toFloat() }
        parser.getAttributeValue(null, "y")?.let { y = it.toFloat() }
    }

//    override fun toText(): ArrayList<Polygon>
//    {
//        /*
//        It seems like when we have many objects inside an artwork
//        these objects can be
//        rectangles
//        sharp polylines
//        sharp polygons
//        circles
//        ellipses
//        texts
//        images
//
//        regular polygons
//
//
//
//        So, what we have defined as Polygon
//        Should now become a transform object
//        that can hold on to a location, rotation and scale of an object
//
//        so, basically the shape nodes
//        which are currently only allowing a path value
//        should also allow for different kinds of objects
//
//         */
//
//        return arrayListOf()
//    }
//
//
//    override fun toPolygon(): ArrayList<Polygon>
//    {
//        val segments: ArrayList<Segment> = arrayListOf()
//
////
////        val moveSeg = Segment(PathType.Move)
////        moveSeg.knot = PointF(x1, y1)
////        segments.add(moveSeg)
////
////        val lineSeg = Segment(PathType.Line)
////        lineSeg.knot = PointF(x2, y2)
////        segments.add(lineSeg)
////
////
////        val polygon: Polygon = Polygon()
////        polygon.shapeNode.pathValue = PathValue(segments)
////        polygon.closed = false
//
////        return arrayListOf(polygon)
//        return arrayListOf()
//    }
}
