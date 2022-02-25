package com.oddinstitute.crossplatformsvgparser.android.svg_parser

import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.InputStream

fun SvgParser.parse(inputStream: InputStream)
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
            parser.name?.let { data.curTagName = parser.name }

            when (eventType)
            {
                XmlPullParser.START_TAG -> tagBegan(parser)
                XmlPullParser.TEXT -> data.curTagText = parser.text // if there is text, read it
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


    // Here, we don't return anything.
    // the assumption is that the artwrok from data is so far populated
}

