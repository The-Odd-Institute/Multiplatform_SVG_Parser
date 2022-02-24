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
//    var polygons: ArrayList<Polygon> = arrayListOf()





    @Transient
    var curFrame: Int = 0

    @Transient
    var thumb: BitmapImage? = null
    var locked: Boolean = false

    var currentMotionBundleID: String = "1"


    // var motionBundles: ArrayList<MotionBundle> = arrayListOf()




    // @Transient var artworkMotion: Motion = Motion("Artwork Motion")

    // var transformNode: TransformNode = TransformNode()

    @Transient
    var selected: Boolean = false

    var hidden: Boolean = false

    @Transient
    var hideForLayerThumbs: Boolean = false


    /*
    constructor(svgFile: SvgFile) : this()  {
        construct (svgFile)
        // this is for saving right at the very beginning
//        saveMotionBundleBeforeNewOrSwitch()
    }

     */
}


