package com.oddinstitute.crossplatformsvgparser.svg_tags


class UseTag(val definedTags: ArrayList<Tag>) : Tag()
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

    fun resultTag(): Tag
    {
        href = href.replace("#", "")
            .trim().trimStart().trimEnd()
            .replace(" ", "")


        val definedTag: Tag = definedTags.first { it.attributes.id == href }

        this.attributes.fill?.let { definedTag.attributes.fill = it }
        this.attributes.strokeWidth?.let { definedTag.attributes.strokeWidth = it }
        this.attributes.fillRule?.let { definedTag.attributes.fillRule = it }
        this.attributes.clipRule?.let { definedTag.attributes.clipRule = it }
        this.attributes.strokeLineCap?.let { definedTag.attributes.strokeLineCap = it }
        this.attributes.strokeLineJoin?.let { definedTag.attributes.strokeLineJoin = it }
        this.attributes.strokeDashArray?.let { definedTag.attributes.strokeDashArray = it }
        this.attributes.transforms?.let { definedTag.attributes.transforms = it }
        this.attributes.style?.let { definedTag.attributes.style = it }
        this.attributes.svgClass?.let { definedTag.attributes.svgClass = it }



        return definedTag
    }
}