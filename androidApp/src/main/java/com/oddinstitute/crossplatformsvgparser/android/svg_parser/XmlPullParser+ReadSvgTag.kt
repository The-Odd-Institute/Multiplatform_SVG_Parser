package com.oddinstitute.crossplatformsvgparser.android.svg_parser

import com.oddinstitute.crossplatformsvgparser.svg_tags.SvgTag
import org.xmlpull.v1.XmlPullParser

fun XmlPullParser.readSvgTag(): SvgTag = SvgTag(this.getAttributeValue(null, "viewBox"))
