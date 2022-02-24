package com.oddinstitute.crossplatformsvgparser

import com.oddinstitute.crossplatformsvgparser.svg_elements.*
import com.oddinstitute.crossplatformsvgparser.svg_tags.SvgAttributesBundle
import com.oddinstitute.crossplatformsvgparser.svg_transform.SvgTransform
import com.oddinstitute.crossplatformsvgparser.svg_transform.decodeTransform
import org.xmlpull.v1.XmlPullParser


fun XmlPullParser.readAttributes(): SvgAttributesBundle
{
    val attr = SvgAttributesBundle()

    this.getAttributeValue(null, "fill")?.let {

        // TODO - Fill should be black by default
        // unless none takes over which makes tansparent
        if (it != "none")
            attr.fill = SvgColor.ofRaw(it)
    }

    this.getAttributeValue(null, "id")?.let {
        attr.id = it.replace("#", "").trim().trimStart().trimEnd()
            .replace(" ", "") // remove hashtags on the fly
    }

    this.getAttributeValue(null, "stroke")?.let {
        if (it != "none")
        {
            attr.stroke = SvgColor.ofRaw(it)
            attr.strokeWidth = 1.0f // if there's a stroke color, then there's a stroke
            // in the next code, this might be updated with the actual stroke width
        }
    }

    this.getAttributeValue(null, "stroke-width")?.let {
        attr.strokeWidth = it.toFloat()
    }

    this.getAttributeValue(null, "stroke-linecap")?.let {
        attr.strokeLineCap = SvgLinecap.ofRaw(it)
    }

    this.getAttributeValue(null, "stroke-dasharray")?.let {
        attr.strokeDashArray = SvgDashArray.ofRaw(it)
        // the SVG dasharray can have multiple entries. They seem meaningless in Android
    }

    this.getAttributeValue(null, "transform")?.let {
        attr.transforms = SvgTransform.decodeTransform(it)
    }

    this.getAttributeValue(null, "fill-rule")?.let {
        attr.fillRule = SvgFillRule.ofRaw(it)
    }

    this.getAttributeValue(null, "clip-rule")?.let {
        attr.clipRule = SvgClipRule.ofRaw(it)
    }

    this.getAttributeValue(null, "stroke-linejoin")?.let {
        attr.strokeLineJoin = SvgStrokeLineJoin.ofRaw(it)
    }

    this.getAttributeValue(null, "style")?.let {
        attr.style = SvgStyle(it)
    }

    this.getAttributeValue(null, "class")?.let {
        attr.svgClass = it // build a class with the name that is on it
    }

    return attr
}