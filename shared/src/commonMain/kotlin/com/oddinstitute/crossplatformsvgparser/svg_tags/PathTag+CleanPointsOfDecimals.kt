package com.oddinstitute.crossplatformsvgparser.svg_tags


fun PathTag.cleanPointsOfDecimals (initialPoints: List<String>): ArrayList<String>
{
    val points: ArrayList<String> = arrayListOf()

    // when we split, it is possible that we get two values clustered into just one.
    // situation: M 1.2.3 is valid and would be parsed like M 1.2 0.3
    // so, we check to see if any of the values has more than two dots


    for (point in initialPoints)
    {
        if (point.filter { it == '.' }.count() == 2)
        {
            val internalTwoDecimalPoints = point.split(".")

            for (i in 0 until internalTwoDecimalPoints.count())
            {
                if (i == 0)
                    points.add("${internalTwoDecimalPoints[i]}.${internalTwoDecimalPoints[i+1]}")
                else if (i == 1)
                    continue
                else
                    points.add("0.${internalTwoDecimalPoints[i]}")
            }
        }
        else
            points.add(point)
    }

    return points
}