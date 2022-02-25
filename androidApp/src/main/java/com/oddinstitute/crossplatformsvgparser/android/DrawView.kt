package com.oddinstitute.crossplatformsvgparser.android

import android.content.Context
import android.graphics.Canvas
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.view.View
import com.oddinstitute.crossplatformsvgparser.to_refactor.Artwork
import com.oddinstitute.crossplatformsvgparser.SvgLineCapToType
import com.oddinstitute.crossplatformsvgparser.SvgLineJoinToType
import com.oddinstitute.crossplatformsvgparser.operators.toFloatArray


class DrawView(context: Context) : View(context)
{
    var artwork: Artwork? = null
    var paint: Paint = Paint()

    fun redraw(art: Artwork)
    {
        this.artwork = art
        this.artwork?.let {
            for (obj in it.objects)
                obj.makePath()
        } // FIXME -> Is this needed. Maybe it causes a double rendering
        this.invalidate()
    }


    override fun onDraw(canvas: Canvas)
    {
        artwork?.let {
            drawArtwork(canvas, it)
        }
    }
}

fun DrawView.drawArtwork(canvas: Canvas, artwork: Artwork)
{ // drawing all paths
    for (obj in artwork.objects)
    { // fill
        obj.shapeAttr.fillColor?.let {

            styleFillPaint(obj)
            canvas.drawPath(obj.myPath.path, paint)
        }


        if (obj.shapeAttr.strokeWidth > 0f)
        {
            styleStrokePaint(obj)
            canvas.drawPath(obj.myPath.path, paint)
        }
    }

}

fun DrawView.styleFillPaint(obj: com.oddinstitute.crossplatformsvgparser.objects.Object)
{
    paint.style = Paint.Style.FILL



    obj.shapeAttr.filColorApplied?.let {
        paint.color = Utils.myColorToArgb(it)
    }

    // paint.color = Color.RED
    paint.strokeCap = SvgLineCapToType(obj.shapeAttr.strokeLineCap)

    paint.strokeJoin = SvgLineJoinToType(obj.shapeAttr.strokeLineJoin)

    paint.pathEffect = null
    paint.clearShadowLayer()
}


// todo important
fun DrawView.styleStrokePaint(obj: com.oddinstitute.crossplatformsvgparser.objects.Object)
{
    obj.shapeAttr.strokeWidth.let { width ->
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = width


        obj.shapeAttr.strokeColorApplied?.let {
            paint.color = Utils.myColorToArgb(it)
        }

        paint.strokeCap = SvgLineCapToType(obj.shapeAttr.strokeLineCap)
        paint.pathEffect = null
        paint.clearShadowLayer()


        // FIXME -> We don't know how this float array works
        // it might have many entires, we are only currnently readn the first
        obj.shapeAttr.dashArray?.let {

            val f = it.toFloatArray()
            if (it.isNotEmpty()) paint.pathEffect = DashPathEffect(floatArrayOf(f[0], f[0]), 0f)

        }

        paint.strokeJoin = SvgLineJoinToType(obj.shapeAttr.strokeLineJoin)

    }
}





