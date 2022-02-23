package com.oddinstitute.crossplatformsvgparser.objects

import com.oddinstitute.crossplatformsvgparser.svg_elements.SvgStyle


fun Object.applySvgStyle(style: SvgStyle)
{
    // these three are in the shape node, because they are animatable
    style.fill?.let { this.shape.fillColor = it }
    style.stroke?.let { this.shape.strokeColor = it }
    style.strokeWidth?.let { this.shape.strokeWidth = it }

    // these are on polygon level, because they are not animateable
    style.fillRule2?.let { svgFillRule ->
        svgFillRule.toType()?.let {
            this.shape.fillType = it
        }
    }

    style.strokeLineCap?.let { svgLinecap ->
        svgLinecap.toType()?.let {
            this.shape.strokeLineCap = it
        }
    }

    style.clipRule?.let { svgClipRule ->
        svgClipRule.toType()?.let {
            this.shape.clipRule = it
        }
    }

    style.strokeDashArray?.let {
        this.shape.dashArray = it
    }

    style.strokeLineJoin?.let { svgStrokeLineJoin ->
        svgStrokeLineJoin.toType()?.let {
            this.shape.strokeLineJoin = it
        }
    }
}