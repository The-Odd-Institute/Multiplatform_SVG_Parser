package com.oddinstitute.crossplatformsvgparser.to_refactor

import com.oddinstitute.crossplatformsvgparser.MyPath2


// this class draws a corner
class CornerPath
{
    var path: MyPath2
    var selected: Boolean = false

    constructor(path: MyPath2)
    {
        this.path = path
    }

    constructor(path: MyPath2, selected: Boolean?)
    {
        this.path = path

        if (selected == true)
            this.selected = selected
    }
}
