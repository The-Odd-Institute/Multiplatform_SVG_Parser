package com.oddinstitute.crossplatformsvgparser.operators

operator fun FloatArray.times(other: Float): FloatArray {

    val outList : ArrayList<Float> = arrayListOf()

    for (any in this)
        outList.add(any * other)


    return outList.toFloatArray()
}