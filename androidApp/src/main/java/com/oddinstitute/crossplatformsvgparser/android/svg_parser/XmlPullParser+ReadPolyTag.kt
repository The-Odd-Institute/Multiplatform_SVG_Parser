package com.oddinstitute.crossplatformsvgparser.android.svg_parser

import com.oddinstitute.crossplatformsvgparser.svg_tags.PolyTag
import org.xmlpull.v1.XmlPullParser


fun XmlPullParser.readPolyTag(closed: Boolean = false): PolyTag
{
    val polyTag = PolyTag()
    polyTag.attributes = this.readAttributes()
    polyTag.closed = closed

    // POLYGON
    this.getAttributeValue(null, "points")?.let { polyTag.points = it }

    // LINE
    this.getAttributeValue(null, "x1")?.let { polyTag.x1 = it.toFloat() }
    this.getAttributeValue(null, "y1")?.let { polyTag.y1 = it.toFloat() }
    this.getAttributeValue(null, "x2")?.let { polyTag.x2 = it.toFloat() }
    this.getAttributeValue(null, "y2")?.let { polyTag.y2 = it.toFloat() }

    // RECT
    this.getAttributeValue(null, "x")?.let { polyTag.x = it.toFloat() }
    this.getAttributeValue(null, "y")?.let { polyTag.y = it.toFloat() }
    this.getAttributeValue(null, "width")?.let { polyTag.width = it.toFloat() }
    this.getAttributeValue(null, "height")?.let { polyTag.height = it.toFloat() }
    this.getAttributeValue(null, "rx")?.let { polyTag.rx = it.toFloat() }
    this.getAttributeValue(null, "ry")?.let { polyTag.ry = it.toFloat() }

    return polyTag
}