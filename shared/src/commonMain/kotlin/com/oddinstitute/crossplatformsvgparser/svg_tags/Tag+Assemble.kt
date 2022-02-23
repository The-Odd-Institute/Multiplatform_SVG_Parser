package com.oddinstitute.crossplatformsvgparser.svg_tags

import android.graphics.PointF
import com.oddinstitute.crossplatformsvgparser.objects.Object
import com.oddinstitute.crossplatformsvgparser.objects.applySvgStyle
import com.oddinstitute.crossplatformsvgparser.svg_elements.SvgStyle
import com.oddinstitute.crossplatformsvgparser.svg_elements.combineWithOuterStyle
import com.oddinstitute.crossplatformsvgparser.svg_transform.SvgTransform

fun Tag.assemble(currentGroups: ArrayList<Tag>,
                 styles: HashMap<String, SvgStyle>?,
                 scaleFactor: Float,
                 viewBoxOffset: PointF): Object?
{
    // Group Level, we make it non-optional (even it initiated, the content might be null which is ok
    var styleByGroup: SvgStyle = SvgStyle()
    var transformByGroup: ArrayList<SvgTransform> = arrayListOf()


    if (currentGroups.count() == 1)
    {
        styleByGroup = currentGroups.first().consolidateStyles(styles)

        currentGroups.first().transforms?.let {
            transformByGroup = it
        }
    }
    else if (currentGroups.count() > 1) {
        for (g in currentGroups) {
            styleByGroup = if (currentGroups.indexOf(g) == 0) {
                g.consolidateStyles(styles)
            }
            else {
                val nextGroupStyle = g.consolidateStyles(styles)
                nextGroupStyle.combineWithOuterStyle(styleByGroup)
            }

            g.transforms?.let {
                transformByGroup.addAll(it)
            }
        }
    }


//    activeGroup?.let { g ->
//        styleByGroup = consolidateStyles(g)
//
//        g.transforms?.let {
//            // group has transform
//            transformByGroup = it
//        }
//    }


    // Local Level, we make it non-optional (even it initiated, the content might be null which is ok
    val styleByElement = this.consolidateStyles(styles)
    var transformByElement: ArrayList<SvgTransform>? = null

    this.transforms?.let {
        transformByElement = it
    }



    // this is the final style of a tag by combining tag level and group level
    // tag comes first and then the group
    val theStyle: SvgStyle = styleByElement.combineWithOuterStyle(styleByGroup)


//    all styles are consolidated properly
//    we are now working on the transforms
    val allTransforms: ArrayList<SvgTransform> = arrayListOf()
    transformByGroup?.let { allTransforms.addAll(it) }
    transformByElement?.let { allTransforms.addAll(it) }


    // by now, we have the style and transform
    // its time to get the object
    this.toObject()?.let {
        it.applySvgStyle(theStyle)

        for (trans in allTransforms.reversed())
            it.svgTransform(trans)


        it.applySvgViewBox(scaleFactor, viewBoxOffset)
        it.roundTwoDecimals()

        return it
    }


    // if we reached here, we return nothing
    return null
}



