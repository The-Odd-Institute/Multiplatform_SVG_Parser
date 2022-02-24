package com.oddinstitute.crossplatformsvgparser.svg_tags

import com.oddinstitute.crossplatformsvgparser.MyVector2
import com.oddinstitute.crossplatformsvgparser.objects.Object
import com.oddinstitute.crossplatformsvgparser.objects.PathObj
import com.oddinstitute.crossplatformsvgparser.operators.cleanTag
import com.oddinstitute.crossplatformsvgparser.to_refactor.Segment
import com.oddinstitute.crossplatformsvgparser.to_refactor.SegmentType
import com.oddinstitute.crossplatformsvgparser.to_refactor.SevenPieceArc
import com.oddinstitute.crossplatformsvgparser.to_refactor.toSegmentsObjCMethod

//
//class PathTag(val parser: XmlPullParser) : Tag(parser)
//{
//    // PATH
//    var d: String? = null
//
//    // OVAL
//    var cx: Float = 0f
//    var cy: Float = 0f
//    var rx: Float? = null
//    var ry: Float? = null
//    var r: Float? = null
//
//
//    init
//    {
//        // PATH
//        parser.getAttributeValue(null, "d")?.let { d = it }
//
//        // OVAL
//        parser.getAttributeValue(null, "rx")?.let { rx = it.toFloat() }
//        parser.getAttributeValue(null, "ry")?.let { ry = it.toFloat() }
//        parser.getAttributeValue(null, "cx")?.let { cx = it.toFloat() }
//        parser.getAttributeValue(null, "cy")?.let { cy = it.toFloat() }
//        parser.getAttributeValue(null, "r")?.let { r = it.toFloat() }
//    }
//
//
//    override fun toObject(): Object?
//    {
//        d?.let { // PATH
//
//            val singlePolyString = it.cleanTag()
//
//            // we shouldn't make an object
//            // we should make a path, the subclass ofObject
//
//            val delimiters: Array<Char> =
//                arrayOf('M', 'm', 'L', 'l', 'C', 'c', 'S', 's', 'Q', 'q', 'T', 't', 'H', 'h', 'V',
//                        'v', 'a', 'A')
//
//
//            // we make them open by default.
//            // for some reason in the init, i have it as true
//            // not sure why
//            // FIXME -? This is random?
//            val closed: Boolean = singlePolyString.contains("Z")
//            val pathObj: PathObj = PathObj(closed)
//
//
//            val workingString = singlePolyString
//                    .replace("Z", "")
//                    .replace("z", "")
//
//
//            val piecesStringArr = arrayListOf(workingString)
//
//            for (del in delimiters)
//            {
//                val temp = ArrayList<String>()
//                temp.addAll(piecesStringArr.filterNotNull())
//                piecesStringArr.clear()
//
//                for (any in temp)
//                {
//                    val thesePieces =
//                        any.split("(?=$del)".toRegex())
//                                .toTypedArray()
//
//                    for (each in thesePieces)
//                        if (each.isNotEmpty())
//                            piecesStringArr.add("$each")
//                }
//            }
//
//
//            var curPoint = MyVector2() // is the previous location
//
//            for (piece: String in piecesStringArr)
//            {
//                when (piece[0])
//                {
//                    'M', 'm' -> pathObj.segments.add(movePiece(piece, curPoint))
//                    'L', 'l', 'v', 'V', 'h', 'H' -> pathObj.segments.addAll(
//                            linePiece(piece, curPoint))
//                    'C', 'c' -> pathObj.segments.addAll(curvePiece(piece, curPoint))
//                    'a', 'A' -> pathObj.segments.addAll(arcPieces(piece, curPoint))
//                    'S', 's' -> pathObj.segments.addAll(
//                            smoothCurvePiece(piece, curPoint, pathObj.segments.last()))
//                    'Q', 'q' -> pathObj.segments.addAll(quadPiece(piece, curPoint))
//                    'T', 't' -> pathObj.segments.add(
//                            smoothQuadPiece(piece, curPoint, pathObj.segments.last()))
//                }
//
//                if (pathObj.segments.count() != 0)
//                    curPoint = pathObj.segments.last().v
//            }
//
//            return pathObj
//        }
//
//
//        var radX = 0f
//        var radY = 0f
//
//        if (r != null || rx != null || ry != null)
//        {
//            r?.let { radX = it; radY = it }
//
//            rx?.let {  radX = it; radY = it }
//
//            ry?.let { radY = it }
//
//
//
//            val obj: PathObj = PathObj(true) // ovals are closed
//
//            val moveSeg = Segment(SegmentType.Move)
//            moveSeg.v = MyVector2(cx - radX, cy)
//            obj.segments.add(moveSeg)
//
//            // XCODE METHOD
//            // FIXME this works, converts a circle into 4 arcs
//            // for now, I am choosing to use the 4 arc method
//
//            // these are the seven pieces of an arc
//            val sevenPieceArc1: SevenPieceArc = SevenPieceArc(radX, radY,
//                                                              0f,
//                                                              largeArcFlag = false,
//                                                              sweepFlag = false,
//                                                              x2 = cx + radX,
//                                                              y2 = cy)
//
//            val sevenPieceArc2: SevenPieceArc = SevenPieceArc(radX, radY,
//                                                              0f,
//                                                              largeArcFlag = true,
//                                                              sweepFlag = false,
//                                                              x2 = cx - radX,
//                                                              y2 = cy)
//
//
//            // THIS IS CURRENTLY THE WORKING ONE THAT CONVERTS to 4 PIECES
//            // first from Move draw to half
//            val piece1Segments = sevenPieceArc1.toSegmentsObjCMethod(MyVector2(cx - radX, cy))
////
////        // then from the end of that half, draw back
//            val piece2Segments = sevenPieceArc2.toSegmentsObjCMethod(MyVector2(cx + radX, cy))
//
//
//            // THIS IS THE OLD JAVA METHOD THAT CONVERTS to 2 PIECES
////        val piece1Segments = sevenPieceArc1.toSegmentsJavaMethod(PointF(p.x - r, p.y))
////        val piece2Segments = sevenPieceArc2.toSegmentsJavaMethod(PointF(p.x + r, p.y))
//
//            obj.segments.addAll(piece1Segments)
//            obj.segments.addAll(piece2Segments)
//
//            return obj
//        }
//
//        return null
//    }
//}


class PathTag() : Tag()
{
    // PATH
    var d: String? = null

    // OVAL
    var cx: Float = 0f
    var cy: Float = 0f
    var rx: Float? = null
    var ry: Float? = null
    var r: Float? = null


    //    init
    //    {
    //        // PATH
    //        parser.getAttributeValue(null, "d")?.let { d = it }
    //
    //        // OVAL
    //        parser.getAttributeValue(null, "rx")?.let { rx = it.toFloat() }
    //        parser.getAttributeValue(null, "ry")?.let { ry = it.toFloat() }
    //        parser.getAttributeValue(null, "cx")?.let { cx = it.toFloat() }
    //        parser.getAttributeValue(null, "cy")?.let { cy = it.toFloat() }
    //        parser.getAttributeValue(null, "r")?.let { r = it.toFloat() }
    //    }


    override fun toObject(): Object?
    {
        d?.let { // PATH

            val singlePolyString = it.cleanTag()

            // we shouldn't make an object
            // we should make a path, the subclass ofObject

            val delimiters: Array<Char> =
                arrayOf('M', 'm', 'L', 'l', 'C', 'c', 'S', 's', 'Q', 'q', 'T', 't', 'H', 'h', 'V',
                        'v', 'a', 'A')


            // we make them open by default.
            // for some reason in the init, i have it as true
            // not sure why
            // FIXME -? This is random?
            val closed: Boolean = singlePolyString.contains("Z")
            val pathObj: PathObj = PathObj(closed)


            val workingString = singlePolyString
                .replace("Z", "")
                .replace("z", "")


            val piecesStringArr = arrayListOf(workingString)

            for (del in delimiters)
            {
                val temp = ArrayList<String>()
                temp.addAll(piecesStringArr.filterNotNull())
                piecesStringArr.clear()

                for (any in temp)
                {
                    val thesePieces =
                        any.split("(?=$del)".toRegex())
                            .toTypedArray()

                    for (each in thesePieces)
                        if (each.isNotEmpty())
                            piecesStringArr.add("$each")
                }
            }


            var curPoint = MyVector2() // is the previous location

            for (piece: String in piecesStringArr)
            {
                when (piece[0])
                {
                    'M', 'm' -> pathObj.segments.add(movePiece(piece, curPoint))
                    'L', 'l', 'v', 'V', 'h', 'H' -> pathObj.segments.addAll(
                            linePiece(piece, curPoint))
                    'C', 'c' -> pathObj.segments.addAll(curvePiece(piece, curPoint))
                    'a', 'A' -> pathObj.segments.addAll(arcPieces(piece, curPoint))
                    'S', 's' -> pathObj.segments.addAll(
                            smoothCurvePiece(piece, curPoint, pathObj.segments.last()))
                    'Q', 'q' -> pathObj.segments.addAll(quadPiece(piece, curPoint))
                    'T', 't' -> pathObj.segments.add(
                            smoothQuadPiece(piece, curPoint, pathObj.segments.last()))
                }

                if (pathObj.segments.count() != 0)
                    curPoint = pathObj.segments.last().v
            }

            return pathObj
        }


        var radX = 0f
        var radY = 0f

        if (r != null || rx != null || ry != null)
        {
            r?.let { radX = it; radY = it }

            rx?.let {  radX = it; radY = it }

            ry?.let { radY = it }



            val obj: PathObj = PathObj(true) // ovals are closed

            val moveSeg = Segment(SegmentType.Move)
            moveSeg.v = MyVector2(cx - radX, cy)
            obj.segments.add(moveSeg)

            // XCODE METHOD
            // FIXME this works, converts a circle into 4 arcs
            // for now, I am choosing to use the 4 arc method

            // these are the seven pieces of an arc
            val sevenPieceArc1: SevenPieceArc = SevenPieceArc(radX, radY,
                                                              0f,
                                                              largeArcFlag = false,
                                                              sweepFlag = false,
                                                              x2 = cx + radX,
                                                              y2 = cy)

            val sevenPieceArc2: SevenPieceArc = SevenPieceArc(radX, radY,
                                                              0f,
                                                              largeArcFlag = true,
                                                              sweepFlag = false,
                                                              x2 = cx - radX,
                                                              y2 = cy)


            // THIS IS CURRENTLY THE WORKING ONE THAT CONVERTS to 4 PIECES
            // first from Move draw to half
            val piece1Segments = sevenPieceArc1.toSegmentsObjCMethod(MyVector2(cx - radX, cy))
            //
            //        // then from the end of that half, draw back
            val piece2Segments = sevenPieceArc2.toSegmentsObjCMethod(MyVector2(cx + radX, cy))


            // THIS IS THE OLD JAVA METHOD THAT CONVERTS to 2 PIECES
            //        val piece1Segments = sevenPieceArc1.toSegmentsJavaMethod(PointF(p.x - r, p.y))
            //        val piece2Segments = sevenPieceArc2.toSegmentsJavaMethod(PointF(p.x + r, p.y))

            obj.segments.addAll(piece1Segments)
            obj.segments.addAll(piece2Segments)

            return obj
        }

        return null
    }
}