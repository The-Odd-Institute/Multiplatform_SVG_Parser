package com.oddinstitute.crossplatformsvgparser.operators

// this cleaning can only be used for Style, d of Path and polylines
fun String.cleanTag(): String
{
    var newString = this.trimIndent()
            .trimStart()
            .trimEnd()
            .trim()
            .replace("\n", " ")
            .replace("\\s+".toRegex(), " ") // remove multiple spaces
            .replace(", ", ",")
        .replace("z", "Z")

    // If the remaining still includes Z, it's closed. Otherwise open


    // the minus situation is only happening in the path
    // so, we test if a minus has two numbers by its sides, we replace it
    var tempMinusString = ""
    for (i in newString.indices)
    {
        if (newString[i] == '-')
        {
            if (i > 0 && newString[i - 1].isDigit())
            {
                if (newString[i + 1].isDigit() || newString[i + 1] == '.') // 27.9335937-.515625
                    tempMinusString += " -" // in this situation add the "space minus"
            }
            else
                tempMinusString += newString[i] // otherwise add the character
        }
        else
            tempMinusString += newString[i] // otherwise add the character
    }
    newString = tempMinusString // this is now the working string


    return newString
}


fun String.toFloatArray (): FloatArray
{
    val outList : ArrayList<Float> = arrayListOf()

    val cleanText = this.replace(" ", ",")

    val pieces = cleanText.split(",")

    for (any in pieces)
    {
        var numeric =  any.matches("-?\\d+(\\.\\d+)?".toRegex())
        outList.add(numeric.toFloat())
    }

    return outList.toFloatArray()

}