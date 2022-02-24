package com.oddinstitute.crossplatformsvgparser.svg_transform

import com.oddinstitute.crossplatformsvgparser.operators.cleanTag


fun SvgTransform.Decode.decodeTransform(text: String): ArrayList<SvgTransform>
{
    val transforms : ArrayList<SvgTransform> = arrayListOf()

    var cleanString = text.cleanTag()


    var tempMinusString = ""
    for (i in cleanString.indices)
    {
        if (cleanString[i] == ' ')
        {
            tempMinusString += if (cleanString[i+1] == 's' || cleanString[i+1] == 't' || cleanString[i+1] == 'r')
                "|" // this is a space between different transformations
            else
                " " // this is the space between x, and y and r and ....
        }
        else
            tempMinusString += cleanString[i] // otherwise add the character
    }
    cleanString = tempMinusString // this is now the working string




    val transformComponents = cleanString.split("|")
//    we are splitin by space,
//    there is between x and y

    for (each in transformComponents)
    {
        when
        {
            each.contains("translate") -> transforms.add(SvgTransform.decodeTranslate(each))
            each.contains("rotate") -> transforms.add(SvgTransform.decodeRotate(each))
            each.contains("scale") -> transforms.add(SvgTransform.decodeScale(each))
            each.contains("matrix") -> transforms.add(SvgTransform.decodeMatrix(each))
        }
    }

    return transforms
}