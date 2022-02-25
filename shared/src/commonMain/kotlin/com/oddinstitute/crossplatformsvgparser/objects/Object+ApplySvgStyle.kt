package com.oddinstitute.crossplatformsvgparser.objects

import com.oddinstitute.crossplatformsvgparser.svg_elements.SvgStyle


fun Object.applySvgStyle(style: SvgStyle)
{
    // these three are in the shape node, because they are animatable
    style.fill?.let { this.shapeAttr.fillColor = it }
    style.stroke?.let { this.shapeAttr.strokeColor = it }
    style.strokeWidth?.let { this.shapeAttr.strokeWidth = it }

    // these are on polygon level, because they are not animateable
    style.fillRule?.let { svgFillRule ->
        this.shapeAttr.fillType = svgFillRule
//        svgFillRule.toType()?.let {
//            this.shape.fillType = it
//        }
    }


    style.strokeLineCap?.let { svgLinecap ->

        this.shapeAttr.strokeLineCap = svgLinecap
//        svgLinecap.toType()?.let {
//            this.shape.strokeLineCap = it
//        }
    }

    style.clipRule?.let { svgClipRule ->
        this.shapeAttr.clipRule = svgClipRule
//        svgClipRule.toType()?.let {
//            this.shape.clipRule = it
//        }
    }

    style.strokeDashArray?.let {
        this.shapeAttr.dashArray = it
    }

    style.strokeLineJoin?.let { svgStrokeLineJoin ->
        this.shapeAttr.strokeLineJoin = svgStrokeLineJoin
//        svgStrokeLineJoin.toType()?.let {
//            this.shape.strokeLineJoin = it
//        }
    }
}