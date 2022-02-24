package com.oddinstitute.crossplatformsvgparser

import com.oddinstitute.crossplatformsvgparser.svg_elements.SvgStyle
import com.oddinstitute.crossplatformsvgparser.svg_tags.Tag
import com.oddinstitute.crossplatformsvgparser.to_refactor.Artwork

class NewSVGPARSER
{
    // this was moved here, because start and end tags are now in separate functions
    val artwork: Artwork = Artwork()

    // default assumption is 512. If different, we should transform
    // var viewBox: String = "0 0 512 512"
    var scaleFactor = 1.0f // if view box is different, we use the scale factor
    var viewBoxOffset = MyVector2()

    var curTagText: String? = null
    var curTagName: String? = null

    var definitionState = false
    var definitions : ArrayList<Tag> = arrayListOf()

    // if we are currently inside a group, we remember that
    //    var activeGroup: Tag? = null
    var currentGroups: ArrayList<Tag> = arrayListOf()

    // if there are styles at the beginning of the SVG file
    // we place them here
    var styles: HashMap<String, SvgStyle>? = null
}


