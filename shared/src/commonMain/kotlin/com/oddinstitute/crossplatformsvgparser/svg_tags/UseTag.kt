package com.oddinstitute.crossplatformsvgparser.svg_tags

import org.xmlpull.v1.XmlPullParser

class UseTag(val parser: XmlPullParser, val definedTags: ArrayList<Tag>) : Tag(parser)
{
    // USE
    var href = ""

    // RECT
    var x = 0f
    var y = 0f

    // width and height are there in the SVG specifications
    // we don't know what to do with them
    var width = 0f
    var height = 0f



    init
    {
        // RECT and USE
        parser.getAttributeValue(null, "x")?.let { x = it.toFloat() }
        parser.getAttributeValue(null, "y")?.let { y = it.toFloat() }
        parser.getAttributeValue(null, "width")?.let { width = it.toFloat() }
        parser.getAttributeValue(null, "height")?.let { height = it.toFloat() }


    // USE

        parser.getAttributeValue(null, "href")?.let { href = it }
    }

    fun resultTag(): Tag
    {
        href = href.replace("#", "")
            .trim().trimStart().trimEnd()
            .replace(" ", "")


        val definedTag: Tag = definedTags.first { it.id == href }

        this.fill?.let { definedTag.fill = it }
        this.strokeWidth?.let { definedTag.strokeWidth = it }
        this.fillRule?.let { definedTag.fillRule = it }
        this.clipRule?.let { definedTag.clipRule = it }
        this.strokeLineCap?.let { definedTag.strokeLineCap = it }
        this.strokeLineJoin?.let { definedTag.strokeLineJoin = it }
        this.strokeDashArray?.let { definedTag.strokeDashArray = it }
        this.transforms?.let { definedTag.transforms = it }
        this.style?.let { definedTag.style = it }
        this.svgClass?.let { definedTag.svgClass = it }



        return definedTag
    }

}
