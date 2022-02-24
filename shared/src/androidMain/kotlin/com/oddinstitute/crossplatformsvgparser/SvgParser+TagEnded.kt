package com.oddinstitute.crossplatformsvgparser

import com.oddinstitute.crossplatformsvgparser.svg_tags.StyleTag

fun SvgParser.tagEnded()
{
    when (curTagName)
    {
        "style" ->
        {
            val styleTag = StyleTag(curTagText)
            this.styles = styleTag.decodeStyle()
        }

        // when we end the group, it should become inactive
        "g" -> currentGroups.removeLast() //  activeGroup = null

        "defs" -> definitionState = false
    }

    curTagName = null
}