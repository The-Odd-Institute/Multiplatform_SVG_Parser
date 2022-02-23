package com.oddinstitute.crossplatformsvgparser

import android.graphics.Path


// this class draws a corner
class CornerPath
{
    var path: Path
    var selected: Boolean = false

    constructor(path: Path)
    {
        this.path = path
    }

    constructor(path: Path, selected: Boolean?)
    {
        this.path = path

        if (selected == true)
            this.selected = selected
    }
}
