package com.oddinstitute.crossplatformsvgparser.svg_tags

import com.oddinstitute.crossplatformsvgparser.MyVector2
import com.oddinstitute.crossplatformsvgparser.cleanTag
import org.xmlpull.v1.XmlPullParser

// this is the only tag that is not a sub class of Tag
// because it doesn't really need that
class SvgTag(val parser: XmlPullParser)
{
    fun decode(): Pair<MyVector2, Float>
    {
        // we don't know if every SVG has viewbox or not
        // if not, we don't have it.
        var viewBoxString: String? = null
        parser.getAttributeValue(null, "viewBox")?.let {
            viewBoxString = it
        }

        // if there is a viewbox string
        viewBoxString?.let { vbString ->


            // general SVG cleaner
            val viewBoxCleaned = vbString.cleanTag()


            // SVG ViewBox can have either space or commas
            val pieces = viewBoxCleaned
                .replace(" ", ",")
                .replace(",,", ",") // it's possible to get two commas
                .split(",")
                .toTypedArray()


            var zero = 0f
            var one = 0f
            var two = 512f
            var three = 512f

            pieces.let {
                when (it.size)
                {
                    1 -> zero = it[0].toFloat()
                    2 ->
                    {
                        zero = it[0].toFloat(); one = it[1].toFloat()
                    }
                    3 ->
                    {
                        zero = it[0].toFloat(); one = it[1].toFloat(); two = it[2].toFloat()
                    }
                    4 ->
                    {
                        zero = it[0].toFloat(); one = it[1].toFloat(); two = it[2].toFloat(); three =
                        it[3].toFloat()
                    }
                }
            }


            val width = two - zero
            val height = three - one

            var large: Float = height // actual height is the difference
            if (width > large) // actual width is the difference
                large = width

            // our assumption is that all SVGs are read at a size that is 512
            // this is either on the x or y
            val scaleFactor = 512.0f / large
            val offset = MyVector2(zero, one)

            return Pair(offset, scaleFactor)
        }

        // if no view box, we assume defaults
        return Pair(MyVector2(), 1f)
    }
}

