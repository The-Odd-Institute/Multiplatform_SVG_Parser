package com.oddinstitute.crossplatformsvgparser

import kotlin.collections.ArrayList

import java.util.*
import android.graphics.Bitmap
import com.oddinstitute.crossplatformsvgparser.objects.Object
import kotlinx.serialization.Transient


// todo make serialiazble later
// @Serializable
class Artwork()
{
    var title: String = ""
    var id: String = UUID.randomUUID().toString()
    var tags: ArrayList<String> = arrayListOf()

    var objects: ArrayList<Object> = arrayListOf()
//    var polygons: ArrayList<Polygon> = arrayListOf()





    @Transient
    var curFrame: Int = 0

    @Transient
    var thumb: Bitmap? = null
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


