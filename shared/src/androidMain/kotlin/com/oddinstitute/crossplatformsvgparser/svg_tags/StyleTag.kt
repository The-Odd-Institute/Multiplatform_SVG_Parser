package com.oddinstitute.crossplatformsvgparser.svg_tags

import com.oddinstitute.crossplatformsvgparser.cleanTag
import com.oddinstitute.crossplatformsvgparser.svg_elements.SvgStyle

class StyleTag(val styleText: String?) : Tag()
{
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
