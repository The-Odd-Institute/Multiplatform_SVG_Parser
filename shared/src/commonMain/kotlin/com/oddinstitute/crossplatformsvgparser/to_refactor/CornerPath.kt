package com.oddinstitute.crossplatformsvgparser.to_refactor

import com.oddinstitute.crossplatformsvgparser.MyPath


// this class draws a corner
class CornerPath
{
    var path: MyPath
    var selected: Boolean = false

    constructor(path: MyPath)
    {
        this.path = path
    }

    constructor(path: MyPath, selected: Boolean?)
    {
        this.path = path

        if (selected == true)
            this.selected = selected
    }
}
