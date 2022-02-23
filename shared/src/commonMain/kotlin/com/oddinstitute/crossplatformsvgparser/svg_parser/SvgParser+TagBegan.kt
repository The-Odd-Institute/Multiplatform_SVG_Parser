package com.oddinstitute.crossplatformsvgparser.svg_parser

import android.util.Log
import com.oddinstitute.crossplatformsvgparser.svg_tags.*
import com.oddinstitute.crossplatformsvgparser.svg_tags.path_tag.PathTag
import org.xmlpull.v1.XmlPullParser


fun SvgParser.tagBegan(parser: XmlPullParser)
{
    when (curTagName)
    {
        "polyline", "line", "polygon", "rect" -> // if rect or polygone, closed is true
        {
            val polygonTag = PolyTag(parser, (curTagName == "polygon" || curTagName == "rect"))
            if (definitionState)
                definitions.add(polygonTag)
            else
                polygonTag.assemble(currentGroups, styles, scaleFactor,
                                    viewBoxOffset)?.let {
                    artwork.objects.add(it)
                }
        }
        "path", "circle", "ellipse"->
        { // this is different. Doesn't use decode, uues a different type
            val pathTag = PathTag(parser)
            if (definitionState)
                definitions.add(pathTag)
            else
                pathTag.assemble(currentGroups, styles, scaleFactor, viewBoxOffset)?.let {
                    artwork.objects.add(it)
                }
        }
        "use" ->
        {
            // here, we pass the definitions into the ue tag
            val useTag = UseTag(parser, definitions)
            val resultTag = useTag.resultTag()

            resultTag.assemble(currentGroups, styles, scaleFactor, viewBoxOffset)?.let {
                artwork.objects.add(it)
            }
        }
        "defs" -> definitionState = true
        // we set the active group, here, we make a tag with parser
        // and put it in the active group,activeGroup = Tag(parser)
        "g" -> currentGroups.add(Tag(parser))

        "svg" ->
        {
            val svgTag = SvgTag(parser)
            val decodedViewBox = svgTag.decode()
            scaleFactor = decodedViewBox.second
            viewBoxOffset = decodedViewBox.first
        } // must be in start, because its end is the end of the document

        else -> Log.d(SvgParser::class.simpleName, "Wrong: $curTagName")
    }
}