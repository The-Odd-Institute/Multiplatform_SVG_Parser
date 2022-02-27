package com.oddinstitute.crossplatformsvgparser

import com.oddinstitute.crossplatformsvgparser.to_refactor.Segment
import com.oddinstitute.crossplatformsvgparser.objects.ShapeAttr
import platform.CoreGraphics.CGMutablePathRef
import platform.CoreGraphics.CGPathCreateMutable
import platform.CoreGraphics.CGPathRef
import platform.Foundation.NSXMLParser
import platform.Foundation.NSXMLParserDelegateProtocol
import platform.darwin.NSObject


class test: NSObject(), NSXMLParserDelegateProtocol
{
    fun myFun ()
    {
        val x = NSXMLParser ()
        x.parse()
        x.delegate = this
    }


    override fun parser(parser: NSXMLParser, foundCharacters: String)
    {
    }

    override fun parser(parser: NSXMLParser, didEndElement: String, namespaceURI: String?, qualifiedName: String?)
    {
    }

    override fun parser(parser: NSXMLParser,
                        didStartElement: String,
                        namespaceURI: String?,
                        qualifiedName: String?,
                        attributes: Map<Any?, *>)
    {
    }


}


//
//
//actual class MyPath actual constructor()
//{
//
//    actual fun makeCurvePath(shapeAttr: ShapeAttr, closed: Boolean, segments: ArrayList<Segment>)
//    {
//
//    }
//    actual fun makeSharpPath(shapeAttr: ShapeAttr, closed: Boolean, pts: ArrayList<MyVector2>)
//    {
//
//    }
//
//    actual fun cubicTo(ox: Float, oy: Float, ix: Float, iy: Float, x: Float, y: Float)
//    {
//    }
//
//    actual fun lineTo(x: Float, y: Float)
//    {
//    }
//
//    actual fun moveTo(x: Float, y: Float)
//    {
//    }
//}