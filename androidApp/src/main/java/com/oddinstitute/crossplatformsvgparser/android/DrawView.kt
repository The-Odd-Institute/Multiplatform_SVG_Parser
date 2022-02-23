package com.oddinstitute.crossplatformsvgparser.android

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.view.View
import com.oddinstitute.crossplatformsvgparser.Artwork


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
        }


        // FIXME -> Is this needed. Maybe it causes a double rendering
        this.invalidate()
    }




    override fun onDraw(canvas: Canvas)
    {
//        tempPath.moveTo(150f,0f)
//        tempPath.lineTo(121f, 90f)
//        tempPath.lineTo(198f, 35f)
//        tempPath.lineTo(102f, 35f)
//        tempPath.lineTo(179f, 90f)
//        tempPath.close()
//        tempPath.fillType = Path.FillType.EVEN_ODD
//
//
//
//        paint.style = Paint.Style.FILL
//        paint.color = Color.RED
//        paint.clearShadowLayer()
//
//        canvas.drawPath(tempPath, paint)


        artwork?.let {
            drawArtwork(canvas, it)
        }
    }
}

fun DrawView.drawArtwork(canvas: Canvas, artwork: Artwork)
{
    // drawing all paths
    for (obj in artwork.objects)
    {
        // fill
        if (obj.shape.fillColor != Color.valueOf(Color.TRANSPARENT))
        {

            styleFillPaint(obj)
            canvas.drawPath(obj.mainPath,
                            paint)
        }

        if (obj.shape.strokeWidth > 0f)
        {
            styleStrokePaint(obj)
            canvas.drawPath(obj.mainPath,
                            paint)
        }
    }

}

fun DrawView.styleFillPaint(obj: com.oddinstitute.crossplatformsvgparser.objects.Object)
{
    paint.style = Paint.Style.FILL



    obj.shape.filColorApplied.let {
        paint.color = it.toArgb()
    }

    // paint.color = Color.RED
    paint.strokeCap = obj.shape.strokeLineCap

    paint.strokeJoin = obj.shape.strokeLineJoin

    paint.pathEffect = null
    paint.clearShadowLayer()
}


// todo important
fun DrawView.styleStrokePaint(obj: com.oddinstitute.crossplatformsvgparser.objects.Object)
{
    obj.shape.strokeWidth.let { width ->
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = width
        obj.shape.strokeColorApplied.let {
            paint.color = it.toArgb()
        }

        paint.strokeCap = obj.shape.strokeLineCap
        paint.pathEffect = null
        paint.clearShadowLayer()


        // FIXME -> We don't know how this float array works
        // it might have many entires, we are only currnently readn the first
        obj.shape.dashArray?.let {

            if (it.isNotEmpty())
                paint.pathEffect = DashPathEffect(floatArrayOf(it[0], it[0]), 0f)

        }

            paint.strokeJoin = obj.shape.strokeLineJoin

    }
}





