package com.oddinstitute.crossplatformsvgparser

import com.oddinstitute.crossplatformsvgparser.svg_tags.*
import org.xmlpull.v1.XmlPullParser

fun XmlPullParser.readUseTag(definedTags: ArrayList<Tag>): UseTag
{
    val useTag = UseTag(definedTags)

    useTag.attributes = this.readAttributes()

    // USE
    this.getAttributeValue(null, "x")?.let { useTag.x = it.toFloat() }
    this.getAttributeValue(null, "y")?.let { useTag.y = it.toFloat() }
    this.getAttributeValue(null, "width")?.let { useTag.width = it.toFloat() }
    this.getAttributeValue(null, "height")?.let { useTag.height = it.toFloat() }
    this.getAttributeValue(null, "href")?.let { useTag.href = it }

    return useTag
}