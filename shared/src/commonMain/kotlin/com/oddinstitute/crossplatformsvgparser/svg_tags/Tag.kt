package com.oddinstitute.crossplatformsvgparser.svg_tags

import com.oddinstitute.crossplatformsvgparser.objects.Object
import com.oddinstitute.crossplatformsvgparser.svg_tags.SvgAttributesBundle

open class Tag()
{
    var attributes: SvgAttributesBundle = SvgAttributesBundle()

    open fun toObject(): Object? = null
}


