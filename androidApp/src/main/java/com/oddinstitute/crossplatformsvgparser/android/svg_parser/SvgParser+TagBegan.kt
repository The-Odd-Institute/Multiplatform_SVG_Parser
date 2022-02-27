package com.oddinstitute.crossplatformsvgparser.android.svg_parser

import android.util.Log
import com.oddinstitute.crossplatformsvgparser.svg_tags.*
import org.xmlpull.v1.XmlPullParser


fun SvgParser.tagBegan(parser: XmlPullParser)
{
    when (data.curTagName)
    {
        "polyline", "line", "polygon", "rect" -> // if rect or polygone, closed is true
        {
            val newPolyTag = parser.readPolyTag(data.curTagName == "polygon" || data.curTagName == "rect")
            if (data.definitionState)
                data.definitions.add(newPolyTag)
            else
                SVG().assembleTag(newPolyTag, data.currentGroups, data.styles, data.scaleFactor,
                                    data.viewBoxOffset)?.let {
                    data.artwork.objects.add(it)
                }
        }
        "path", "circle", "ellipse"->
        { // this is different. Doesn't use decode, uues a different type

            val newPathTag = parser.readPathTag()
            if (data.definitionState)
                data.definitions.add(newPathTag)
            else
                SVG().assembleTag(newPathTag, data.currentGroups, data.styles, data.scaleFactor, data.viewBoxOffset)?.let {
                    data.artwork.objects.add(it)
                }
        }
        "use" ->
        {
            // here, we pass the definitions into the ue tag
            val useTag = parser.readUseTag(data.definitions)
            val resultTag = useTag.resultTag()

            SVG().assembleTag(resultTag, data.currentGroups, data.styles, data.scaleFactor, data.viewBoxOffset)?.let {
                data.artwork.objects.add(it)
            }
        }
        "defs" -> data.definitionState = true
        // we set the active group, here, we make a tag with parser
        // and put it in the active group,activeGroup = Tag(parser)
        "g" ->
        {
            val tag = Tag ()
            tag.attributes = parser.readAttributes()
            data.currentGroups.add(tag)
        }

        "svg" ->
        {
            val svgTag = parser.readSvgTag()
            val decodedViewBox = svgTag.decode()
            data.scaleFactor = decodedViewBox.second
            data.viewBoxOffset = decodedViewBox.first
        } // must be in start, because its end is the end of the document

        else -> Log.d(com.oddinstitute.crossplatformsvgparser.android.svg_parser.SvgParser::class.simpleName, "Wrong: ${data.curTagName}")
    }
}