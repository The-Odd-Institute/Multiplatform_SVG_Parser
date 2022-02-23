package com.oddinstitute.crossplatformsvgparser.svg_elements

// here, we read inner and outer styles. Inner gets priority
fun SvgStyle.combineWithOuterStyle(outerStyle: SvgStyle): SvgStyle
{
    val resStyle: SvgStyle = SvgStyle()

    // we call all of these on order
    // so elements takes priority over the group
    outerStyle.fill?.let { resStyle.fill = it }
    this.fill?.let { resStyle.fill = it }

    outerStyle.stroke?.let { resStyle.stroke = it }
    this.stroke?.let { resStyle.stroke = it }

    outerStyle.stroke?.let { resStyle.stroke = it }
    this.stroke?.let { resStyle.stroke = it }

    outerStyle.strokeWidth?.let { resStyle.strokeWidth = it }
    this.strokeWidth?.let { resStyle.strokeWidth = it }

    outerStyle.fillRule2?.let { resStyle.fillRule2 = it }
    this.fillRule2?.let { resStyle.fillRule2 = it }

    outerStyle.clipRule?.let { resStyle.clipRule = it }
    this.clipRule?.let { resStyle.clipRule = it }

    outerStyle.strokeLineCap?.let { resStyle.strokeLineCap = it }
    this.strokeLineCap?.let { resStyle.strokeLineCap = it }

    outerStyle.strokeDashArray?.let { resStyle.strokeDashArray = it }
    this.strokeDashArray?.let { resStyle.strokeDashArray = it }

    outerStyle.strokeLineJoin?.let { resStyle.strokeLineJoin = it }
    this.strokeLineJoin?.let { resStyle.strokeLineJoin = it }

    return resStyle
}