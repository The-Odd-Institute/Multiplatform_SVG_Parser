package com.oddinstitute.crossplatformsvgparser

import com.oddinstitute.crossplatformsvgparser.to_refactor.Artwork
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.InputStream

fun SvgParser.parse(inputStream: InputStream): Artwork
{
    try
    {
        val factory = XmlPullParserFactory.newInstance()
        factory.isNamespaceAware = true
        val parser: XmlPullParser = factory.newPullParser()
        parser.setInput(inputStream, null)
        var eventType = parser.eventType

        while (eventType != XmlPullParser.END_DOCUMENT)
        {
            parser.name?.let { curTagName = parser.name }

            when (eventType)
            {
                XmlPullParser.START_TAG -> tagBegan(parser)
                XmlPullParser.TEXT -> curTagText = parser.text // if there is text, read it
                XmlPullParser.END_TAG -> tagEnded ()
            }

            eventType = parser.next()
        }

    } catch (e: XmlPullParserException)
    {
        e.printStackTrace()
    } catch (e: IOException)
    {
        e.printStackTrace()
    }


    // todo THESE HAVE TO BE ADDED LATER ON
    // this.makeDefaultMotionBundle()
    // findPivot()


    return artwork
}

