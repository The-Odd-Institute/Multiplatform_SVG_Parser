package com.oddinstitute.crossplatformsvgparser.to_refactor

import com.oddinstitute.crossplatformsvgparser.BitmapImage
import com.oddinstitute.crossplatformsvgparser.UniqueId
import kotlin.collections.ArrayList

import com.oddinstitute.crossplatformsvgparser.objects.Object
import kotlinx.serialization.Transient


// todo make serialiazble later
// @Serializable
class Artwork()
{
    var title: String = ""
    var id: UniqueId = UniqueId()
    var tags: ArrayList<String> = arrayListOf()
    var objects: ArrayList<Object> = arrayListOf()
}


