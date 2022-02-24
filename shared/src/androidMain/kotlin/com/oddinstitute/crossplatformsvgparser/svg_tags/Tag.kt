package com.oddinstitute.crossplatformsvgparser.svg_tags

import com.oddinstitute.crossplatformsvgparser.MyColor
import com.oddinstitute.crossplatformsvgparser.objects.Object
import com.oddinstitute.crossplatformsvgparser.svg_elements.*
import com.oddinstitute.crossplatformsvgparser.svg_transform.SvgTransform
import com.oddinstitute.crossplatformsvgparser.svg_transform.decodeTransform
import org.xmlpull.v1.XmlPullParser

// base tag class for everyone
// it cacluates ALL the common tags
open class Tag()
{
//    companion object Cleaner
//    {
//
//    }


    open var id: String? = null

    open var fill: MyColor? = null

    open var stroke: MyColor? = null
    open var strokeWidth: Float? = null

    open var fillRule: SvgFillRule? = null
    open var clipRule: SvgClipRule? = null
    open var strokeLineCap: SvgLinecap? = null // butt | round | square
    open var strokeLineJoin: SvgStrokeLineJoin? = null
    open var strokeDashArray: FloatArray? = null // butt | round | square

    open var transforms: ArrayList<SvgTransform>? = null

    open var style: SvgStyle? = null
    open var svgClass: String? = null // this is the only one that's a string.
    // it makes sense (it's not a first tag)

    // this is generic, but only used in the actual tags
    // var parentGroup: GTag? = null

    constructor(parser: XmlPullParser) : this()
    {
        parser.getAttributeValue(null, "fill")?.let {

            // TODO - Fill should be black by default
            // unless none takes over which makes tansparent
            if (it != "none")
                this.fill = SvgColor.ofRaw(it)
        }

        parser.getAttributeValue(null, "id")?.let {
            this.id = it.replace("#", "").trim().trimStart().trimEnd()
                    .replace(" ", "") // remove hashtags on the fly
        }

        parser.getAttributeValue(null, "stroke")?.let {
            if (it != "none")
            {
                this.stroke = SvgColor.ofRaw(it)
                this.strokeWidth = 1.0f // if there's a stroke color, then there's a stroke
                // in the next code, this might be updated with the actual stroke width
            }
        }

        parser.getAttributeValue(null, "stroke-width")?.let {
            this.strokeWidth = it.toFloat()
        }

        parser.getAttributeValue(null, "stroke-linecap")?.let {
            this.strokeLineCap = SvgLinecap.ofRaw(it)
        }

        parser.getAttributeValue(null, "stroke-dasharray")?.let {
            this.strokeDashArray = SvgDashArray.ofRaw(it)
            // the SVG dasharray can have multiple entries. They seem meaningless in Android
        }

        parser.getAttributeValue(null, "transform")?.let {
            this.transforms = SvgTransform.decodeTransform(it)
        }

        parser.getAttributeValue(null, "fill-rule")?.let {
            this.fillRule = SvgFillRule.ofRaw(it)
        }

        parser.getAttributeValue(null, "clip-rule")?.let {
            this.clipRule = SvgClipRule.ofRaw(it)
        }

        parser.getAttributeValue(null, "stroke-linejoin")?.let {
            this.strokeLineJoin = SvgStrokeLineJoin.ofRaw(it)
        }

        parser.getAttributeValue(null, "style")?.let {
            this.style = SvgStyle(it)
        }

        parser.getAttributeValue(null, "class")?.let {
            this.svgClass = it // build a class with the name that is on it
        }
    }



    open fun toObject(): Object?
    {
        return null
    }
}