package com.oddinstitute.crossplatformsvgparser

import android.util.Log
import com.oddinstitute.crossplatformsvgparser.svg_tags.Tag
import com.oddinstitute.crossplatformsvgparser.svg_tags.*
import org.xmlpull.v1.XmlPullParser


fun SvgParser.tagBegan(parser: XmlPullParser)
{
    when (curTagName)
    {
        "polyline", "line", "polygon", "rect" -> // if rect or polygone, closed is true
        {
            val newPolyTag = parser.readPolyTag(curTagName == "polygon" || curTagName == "rect")
            if (definitionState)
                definitions.add(newPolyTag)
            else
                assembleTag(newPolyTag, currentGroups, styles, scaleFactor,
                            viewBoxOffset)?.let {
                    artwork.objects.add(it)
                }
        }
        "path", "circle", "ellipse"->
        { // this is different. Doesn't use decode, uues a different type

            val newPathTag = parser.readPathTag()
            if (definitionState)
                definitions.add(newPathTag)
            else
                assembleTag(newPathTag, currentGroups, styles, scaleFactor, viewBoxOffset)?.let {
                    artwork.objects.add(it)
                }
        }
        "use" ->
        {
            // here, we pass the definitions into the ue tag
            val useTag = parser.readUseTag(definitions)
            val resultTag = useTag.resultTag()

            assembleTag(resultTag, currentGroups, styles, scaleFactor, viewBoxOffset)?.let {
                artwork.objects.add(it)
            }
        }
        "defs" -> definitionState = true
        // we set the active group, here, we make a tag with parser
        // and put it in the active group,activeGroup = Tag(parser)
        "g" ->
        {
            val tag = Tag ()
            tag.attributes = parser.readAttributes()
            currentGroups.add(tag)
        }

        "svg" ->
        {
            val svgTag = parser.readSvgTag()
            val decodedViewBox = svgTag.decode()
            scaleFactor = decodedViewBox.second
            viewBoxOffset = decodedViewBox.first
        } // must be in start, because its end is the end of the document

        else -> Log.d(SvgParser::class.simpleName, "Wrong: $curTagName")
    }
}