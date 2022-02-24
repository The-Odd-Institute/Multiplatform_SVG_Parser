package com.oddinstitute.crossplatformsvgparser.svg_tags

import com.oddinstitute.crossplatformsvgparser.cleanTag
import com.oddinstitute.crossplatformsvgparser.svg_elements.SvgStyle

class StyleTag(val styleText: String?) : Tag()
{
//    companion object
//    {
//        // this function receives a style string and decodes it to actual SvgStyles
//        fun decodeSingleStyle(sText: String): SvgStyle
//        {
//            val svgStyle: SvgStyle = SvgStyle()
//
//            // in style, we have both the Equal sign and the colon,
//            // we replace equals with a colon
//            val components = sText
//                    .replace("=", ":")
//                    .replace("\\s+".toRegex(), "") // remove multiple spaces
//                    .split(";")
//
//            for (each in components)
//            {
//                val keyVal = each.split(":")
//                if (keyVal.count() < 2)
//                    continue
//
//                val key = keyVal[0]
//                val value = keyVal[1]
//
//                when (key)
//                {
//                    "fill" -> svgStyle.fill = SvgColor.ofRaw(value)
//                    "stroke" -> svgStyle.stroke = SvgColor.ofRaw(value)
//                    "stroke-width" -> svgStyle.strokeWidth = value.toFloat()
//                    "fill-rule" -> svgStyle.fillRule = SvgFillRule.ofRaw(value) // gets from enum
//                    "clip-rule" -> svgStyle.clipRule = SvgClipRule.ofRaw(value)
//                    "stroke-linecap" -> svgStyle.strokeLineCap = SvgLinecap.ofRaw(value)
//                    "stroke-dasharray" -> svgStyle.strokeDashArray = value.toFloat()
//                    "stroke-linejoin" -> svgStyle.strokeLineJoin = SvgStrokeLineJoin.valueOf(value)
//                }
//            }
//
//            return svgStyle
//        }
//
//    }


    // this tag reads the styles
    // associated with a SVG file

    fun decodeStyle(): HashMap<String, SvgStyle>
    {
        val stylesMap: HashMap<String, SvgStyle> = hashMapOf()

        styleText?.let {


            // this section replaces the period at the beginning of styles with a "|", so
            // we can use that to split the style
            var styleString: String = ""
            for (i in styleText.indices)
            {
                var char = styleText[i] // this character

                if (char == '.') // is a period
                {
                    if (i + 1 < styleText.count()) // there's next
                    {
                        if (!styleText[i + 1].isDigit()) // if the next one is not digit
                        {
                            // this is the starting ., let's replace
                            char = '|'
                        }
                    }
                }

                styleString += char
            }


            val pieces = styleString.split("|")
                    .toTypedArray()

            for (any in pieces)
            {
                val cleanPiece = any.cleanTag()

                if (cleanPiece.isEmpty())
                    continue


                val content =
                    cleanPiece.split("{")
                            .toTypedArray()

                val name = content[0]


                val values: String = content[1].replace("}", "")


                // here, we separate the value decoder, because some elements might have
                // inline styles. We need the function for those as well.
                val svgStyle: SvgStyle = SvgStyle(values)


                stylesMap[name] = svgStyle
            }
        }


        return stylesMap
    }

}
