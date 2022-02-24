package com.oddinstitute.crossplatformsvgparser

import com.oddinstitute.crossplatformsvgparser.svg_tags.PathTag
import org.xmlpull.v1.XmlPullParser


fun XmlPullParser.readPathTag(): PathTag
{
    val pathTag = PathTag()
    pathTag.attributes = this.readAttributes()


    // PATH
    this.getAttributeValue(null, "d")?.let { pathTag.d = it }

    // OVAL
    this.getAttributeValue(null, "rx")?.let { pathTag.rx = it.toFloat() }
    this.getAttributeValue(null, "ry")?.let { pathTag.ry = it.toFloat() }
    this.getAttributeValue(null, "cx")?.let { pathTag.cx = it.toFloat() }
    this.getAttributeValue(null, "cy")?.let { pathTag.cy = it.toFloat() }
    this.getAttributeValue(null, "r")?.let { pathTag.r = it.toFloat() }


    return pathTag
}