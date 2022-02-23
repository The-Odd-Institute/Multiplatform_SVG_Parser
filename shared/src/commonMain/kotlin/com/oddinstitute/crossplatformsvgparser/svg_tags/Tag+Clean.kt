package com.oddinstitute.crossplatformsvgparser.svg_tags

// this cleaning can only be used for Style, d of Path and polylines
fun Tag.Cleaner.clean(text: String): String
{

    var newString = text.trimIndent()
            .trimStart()
            .trimEnd()
            .trim()
            .replace("\n", " ")
            .replace("\\s+".toRegex(), " ") // remove multiple spaces
            .replace(", ", ",")

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