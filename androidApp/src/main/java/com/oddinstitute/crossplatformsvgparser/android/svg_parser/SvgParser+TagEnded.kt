package com.oddinstitute.crossplatformsvgparser.android.svg_parser

import com.oddinstitute.crossplatformsvgparser.svg_tags.StyleTag

fun SvgParser.tagEnded()
{
    when (data.curTagName)
    {
        "style" ->
        {
            val styleTag = StyleTag(data.curTagText)
            this.data.styles = styleTag.decodeStyle()
        }

        // when we end the group, it should become inactive
        "g" -> data.currentGroups.removeLast() //  activeGroup = null

        "defs" -> data.definitionState = false
    }

    data.curTagName = null
}