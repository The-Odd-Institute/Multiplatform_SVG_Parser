package com.oddinstitute.crossplatformsvgparser.svg_elements

import com.oddinstitute.crossplatformsvgparser.operators.toFloat

class SvgDashArray()
{
    /*
    If an odd number of values is provided, then the list of values is repeated to yield an even number of values.
    Thus, 5,3,2 is equivalent to 5,3,2,5,3,2.
     */
    companion object
    {
        fun ofRaw (text: String): FloatArray
        {
            val outList : ArrayList<Float> = arrayListOf()

            val cleanText = text.replace(" ", ",")

            val pieces = cleanText.split(",")

            for (any in pieces)
            {
                var numeric = true

                numeric = any.matches("-?\\d+(\\.\\d+)?".toRegex())

                outList.add(numeric.toFloat())

            }

            return outList.toFloatArray()

        }
    }
}