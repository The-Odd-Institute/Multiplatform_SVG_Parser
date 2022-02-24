package com.oddinstitute.crossplatformsvgparser.svg_tags

import com.oddinstitute.crossplatformsvgparser.svg_elements.SvgStyle


// this function takes three possible levels of elements, style and class for any tag
// and returns the consolidated one based on the following:
// Style Overrides class
// Class overrides individual elements

fun consolidateTagStyles(tag: Tag, styles: HashMap<String, SvgStyle>?): SvgStyle
{
    var styleLevel: SvgStyle = SvgStyle() // if there's a style
    var classLevel: SvgStyle = SvgStyle() // if there's a class
    val elemLevel: SvgStyle = SvgStyle() // if there are inline values


    tag.svgClass?.let { svgClass ->
        // there is a class
        // let's find out the style
        styles?.let { theStyles ->
            theStyles[svgClass]?.let {
                classLevel = it
            }
        }
    }

    tag.style?.let {
        styleLevel = it
    }


    // here, we don't directly set them
    // we only do if value exists
    // WHY
    tag.fill?.let { elemLevel.fill = it }
    tag.stroke?.let { elemLevel.stroke = it }
    tag.strokeWidth?.let { elemLevel.strokeWidth = it }
    tag.fillRule?.let { elemLevel.fillRule2 = it }
    tag.clipRule?.let { elemLevel.clipRule = it }
    tag.strokeLineCap?.let { elemLevel.strokeLineCap = it }
    tag.strokeDashArray?.let { elemLevel.strokeDashArray = it }
    tag.strokeLineJoin?.let { elemLevel.strokeLineJoin = it }



    // NOW, LET'S COMBINE
    // inline has -> direct
    // inline doesn't, style has
    // inline doesn't, style doesn't, class has

    val outStyle: SvgStyle = SvgStyle()


    // we call these IN Order. So, if there is a class, it'll override elements
    // and if there is a style, it'll override everything

    elemLevel.fill?.let { outStyle.fill = it }
    classLevel.fill?.let { outStyle.fill = it }
    styleLevel.fill?.let { outStyle.fill = it }


    elemLevel.stroke?.let { outStyle.stroke = it }
    classLevel.stroke?.let { outStyle.stroke = it }
    styleLevel.stroke?.let { outStyle.stroke = it }

    elemLevel.strokeWidth?.let { outStyle.strokeWidth = it }
    classLevel.strokeWidth?.let { outStyle.strokeWidth = it }
    styleLevel.strokeWidth?.let { outStyle.strokeWidth = it }


    elemLevel.fillRule2?.let { outStyle.fillRule2 = it }
    classLevel.fillRule2?.let { outStyle.fillRule2 = it }
    styleLevel.fillRule2?.let { outStyle.fillRule2 = it }


    elemLevel.clipRule?.let { outStyle.clipRule = it }
    classLevel.clipRule?.let { outStyle.clipRule = it }
    styleLevel.clipRule?.let { outStyle.clipRule = it }



    elemLevel.strokeLineCap?.let { outStyle.strokeLineCap = it }
    classLevel.strokeLineCap?.let { outStyle.strokeLineCap = it }
    styleLevel.strokeLineCap?.let { outStyle.strokeLineCap = it }

    elemLevel.strokeLineJoin?.let { outStyle.strokeLineJoin = it }
    classLevel.strokeLineJoin?.let { outStyle.strokeLineJoin = it }
    styleLevel.strokeLineJoin?.let { outStyle.strokeLineJoin = it }


    elemLevel.strokeDashArray?.let { outStyle.strokeDashArray = it }
    classLevel.strokeDashArray?.let { outStyle.strokeDashArray = it }
    styleLevel.strokeDashArray?.let { outStyle.strokeDashArray = it }


    return outStyle
}

