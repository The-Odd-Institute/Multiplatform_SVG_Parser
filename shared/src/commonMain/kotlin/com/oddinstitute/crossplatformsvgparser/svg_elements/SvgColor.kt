package com.oddinstitute.crossplatformsvgparser.svg_elements

import android.graphics.Color
import com.oddinstitute.crossplatformsvgparser.operators.roundTwoDecimals

/*
 #rgb | #CDF
 #rrggbb | #CD853F
 rgb(rrr, ggg, bbb) | rgb(205,133,63)
 rgb(R%, G%, B%) | rgb(80.392%, 52.157%, 24.706%)
 keyword | peru

 */

data class RGB (val r: Int, val g: Int, val b: Int)

enum class SvgColor(val rgb: RGB)
{
    lightpink(RGB(255, 182, 193)),
    lightsalmon(RGB(255, 160, 122)),
    lightseagreen(RGB(32, 178, 170)),
    lightskyblue(RGB(135, 206, 250)),
    lightslategray(RGB(119, 136, 153)),
    lightslategrey(RGB(119, 136, 153)),
    lightsteelblue(RGB(176, 196, 222)),
    lightyellow(RGB(255, 255, 224)),
    lime(RGB(0, 255, 0)),
    limegreen(RGB(50, 205, 50)),
    linen(RGB(250, 240, 230)),
    magenta(RGB(255, 0, 255)),
    maroon(RGB(128, 0, 0)),
    mediumaquamarine(RGB(102, 205, 170)),
    mediumblue(RGB(0, 0, 205)),
    mediumorchid(RGB(186, 85, 211)),
    mediumpurple(RGB(147, 112, 219)),
    mediumseagreen(RGB(60, 179, 113)),
    mediumslateblue(RGB(123, 104, 238)),
    mediumspringgreen(RGB(0, 250, 154)),
    mediumturquoise(RGB(72, 209, 204)),
    mediumvioletred(RGB(199, 21, 133)),
    midnightblue(RGB(25, 25, 112)),
    mintcream(RGB(245, 255, 250)),
    mistyrose(RGB(255, 228, 225)),
    moccasin(RGB(255, 228, 181)),
    navajowhite(RGB(255, 222, 173)),
    navy(RGB(0, 0, 128)),
    oldlace(RGB(253, 245, 230)),
    olive(RGB(128, 128, 0)),
    olivedrab(RGB(107, 142, 35)),
    orange(RGB(255, 165, 0)),
    orangered(RGB(255, 69, 0)),
    orchid(RGB(218, 112, 214)),
    palegoldenrod(RGB(238, 232, 170)),
    palegreen(RGB(152, 251, 152)),
    paleturquoise(RGB(175, 238, 238)),
    palevioletred(RGB(219, 112, 147)),
    papayawhip(RGB(255, 239, 213)),
    peachpuff(RGB(255, 218, 185)),
    peru(RGB(205, 133, 63)),
    pink(RGB(255, 192, 203)),
    plum(RGB(221, 160, 221)),
    powderblue(RGB(176, 224, 230)),
    purple(RGB(128, 0, 128)),
    red(RGB(255, 0, 0)),
    rosybrown(RGB(188, 143, 143)),
    royalblue(RGB(65, 105, 225)),
    saddlebrown(RGB(139, 69, 19)),
    salmon(RGB(250, 128, 114)),
    sandybrown(RGB(244, 164, 96)),
    seagreen(RGB(46, 139, 87)),
    seashell(RGB(255, 245, 238)),
    sienna(RGB(160, 82, 45)),
    silver(RGB(192, 192, 192)),
    skyblue(RGB(135, 206, 235)),
    slateblue(RGB(106, 90, 205)),
    slategray(RGB(112, 128, 144)),
    slategrey(RGB(112, 128, 144)),
    snow(RGB(255, 250, 250)),
    springgreen(RGB(0, 255, 127)),
    steelblue(RGB(70, 130, 180)),
    tan(RGB(210, 180, 140)),
    teal(RGB(0, 128, 128)),
    thistle(RGB(216, 191, 216)),
    tomato(RGB(255, 99, 71)),
    turquoise(RGB(64, 224, 208)),
    violet(RGB(238, 130, 238)),
    wheat(RGB(245, 222, 179)),
    white(RGB(255, 255, 255)),
    whitesmoke(RGB(245, 245, 245)),
    yellow(RGB(255, 255, 0)),
    yellowgreen(RGB(154, 205, 50)),
    aliceblue(RGB(240, 248, 255)),
    antiquewhite(RGB(250, 235, 215)),
    aqua(RGB(0, 255, 255)),
    aquamarine(RGB(127, 255, 212)),
    azure(RGB(240, 255, 255)),
    beige(RGB(245, 245, 220)),
    bisque(RGB(255, 228, 196)),
    black(RGB(0, 0, 0)),
    blanchedalmond(RGB(255, 235, 205)),
    blue(RGB(0, 0, 255)),
    blueviolet(RGB(138, 43, 226)),
    brown(RGB(165, 42, 42)),
    burlywood(RGB(222, 184, 135)),
    cadetblue(RGB(95, 158, 160)),
    chartreuse(RGB(127, 255, 0)),
    chocolate(RGB(210, 105, 30)),
    coral(RGB(255, 127, 80)),
    cornflowerblue(RGB(100, 149, 237)),
    cornsilk(RGB(255, 248, 220)),
    crimson(RGB(220, 20, 60)),
    cyan(RGB(0, 255, 255)),
    darkblue(RGB(0, 0, 139)),
    darkcyan(RGB(0, 139, 139)),
    darkgoldenrod(RGB(184, 134, 11)),
    darkgray(RGB(169, 169, 169)),
    darkgreen(RGB(0, 100, 0)),
    darkgrey(RGB(169, 169, 169)),
    darkkhaki(RGB(189, 183, 107)),
    darkmagenta(RGB(139, 0, 139)),
    darkolivegreen(RGB(85, 107, 47)),
    darkorange(RGB(255, 140, 0)),
    darkorchid(RGB(153, 50, 204)),
    darkred(RGB(139, 0, 0)),
    darksalmon(RGB(233, 150, 122)),
    darkseagreen(RGB(143, 188, 143)),
    darkslateblue(RGB(72, 61, 139)),
    darkslategray(RGB(47, 79, 79)),
    darkslategrey(RGB(47, 79, 79)),
    darkturquoise(RGB(0, 206, 209)),
    darkviolet(RGB(148, 0, 211)),
    deeppink(RGB(255, 20, 147)),
    deepskyblue(RGB(0, 191, 255)),
    dimgray(RGB(105, 105, 105)),
    dimgrey(RGB(105, 105, 105)),
    dodgerblue(RGB(30, 144, 255)),
    firebrick(RGB(178, 34, 34)),
    floralwhite(RGB(255, 250, 240)),
    forestgreen(RGB(34, 139, 34)),
    fuchsia(RGB(255, 0, 255)),
    gainsboro(RGB(220, 220, 220)),
    ghostwhite(RGB(248, 248, 255)),
    gold(RGB(255, 215, 0)),
    goldenrod(RGB(218, 165, 32)),
    gray(RGB(128, 128, 128)),
    grey(RGB(128, 128, 128)),
    green(RGB(0, 128, 0)),
    greenyellow(RGB(173, 255, 47)),
    honeydew(RGB(240, 255, 240)),
    hotpink(RGB(255, 105, 180)),
    indianred(RGB(205, 92, 92)),
    indigo(RGB(75, 0, 130)),
    ivory(RGB(255, 255, 240)),
    khaki(RGB(240, 230, 140)),
    lavender(RGB(230, 230, 250)),
    lavenderblush(RGB(255, 240, 245)),
    lawngreen(RGB(124, 252, 0)),
    lemonchiffon(RGB(255, 250, 205)),
    lightblue(RGB(173, 216, 230)),
    lightcoral(RGB(240, 128, 128)),
    lightcyan(RGB(224, 255, 255)),
    lightgoldenrodyellow(RGB(250, 250, 210)),
    lightgray(RGB(211, 211, 211)),
    lightgreen(RGB(144, 238, 144)),
    lightgrey(RGB(211, 211, 211)),
    none(RGB(0, 0, 0));


    override fun toString(): String
    {
        when (this)
        {
            lightpink -> return "lightpink"
            lightsalmon -> return "lightsalmon"
            lightseagreen -> return "lightseagreen"
            lightskyblue -> return "lightskyblue"
            lightslategray -> return "lightslategray"
            lightslategrey -> return "lightslategrey"
            lightsteelblue -> return "lightsteelblue"
            lightyellow -> return "lightyellow"
            lime -> return "lime"
            limegreen -> return "limegreen"
            linen -> return "linen"
            magenta -> return "magenta"
            maroon -> return "maroon"
            mediumaquamarine -> return "mediumaquamarine"
            mediumblue -> return "mediumblue"
            mediumorchid -> return "mediumorchid"
            mediumpurple -> return "mediumpurple"
            mediumseagreen -> return "mediumseagreen"
            mediumslateblue -> return "mediumslateblue"
            mediumspringgreen -> return "mediumspringgreen"
            mediumturquoise -> return "mediumturquoise"
            mediumvioletred -> return "mediumvioletred"
            midnightblue -> return "midnightblue"
            mintcream -> return "mintcream"
            mistyrose -> return "mistyrose"
            moccasin -> return "moccasin"
            navajowhite -> return "navajowhite"
            navy -> return "navy"
            oldlace -> return "oldlace"
            olive -> return "olive"
            olivedrab -> return "olivedrab"
            orange -> return "orange"
            orangered -> return "orangered"
            orchid -> return "orchid"
            palegoldenrod -> return "palegoldenrod"
            palegreen -> return "palegreen"
            paleturquoise -> return "paleturquoise"
            palevioletred -> return "palevioletred"
            papayawhip -> return "papayawhip"
            peachpuff -> return "peachpuff"
            peru -> return "peru"
            pink -> return "pink"
            plum -> return "plum"
            powderblue -> return "powderblue"
            purple -> return "purple"
            red -> return "red"
            rosybrown -> return "rosybrown"
            royalblue -> return "royalblue"
            saddlebrown -> return "saddlebrown"
            salmon -> return "salmon"
            sandybrown -> return "sandybrown"
            seagreen -> return "seagreen"
            seashell -> return "seashell"
            sienna -> return "sienna"
            silver -> return "silver"
            skyblue -> return "skyblue"
            slateblue -> return "slateblue"
            slategray -> return "slategray"
            slategrey -> return "slategrey"
            snow -> return "snow"
            springgreen -> return "springgreen"
            steelblue -> return "steelblue"
            tan -> return "tan"
            teal -> return "teal"
            thistle -> return "thistle"
            tomato -> return "tomato"
            turquoise -> return "turquoise"
            violet -> return "violet"
            wheat -> return "wheat"
            white -> return "white"
            whitesmoke -> return "whitesmoke"
            yellow -> return "yellow"
            yellowgreen -> return "yellowgreen"
            aliceblue -> return "aliceblue"
            antiquewhite -> return "antiquewhite"
            aqua -> return "aqua"
            aquamarine -> return "aquamarine"
            azure -> return "azure"
            beige -> return "beige"
            bisque -> return "bisque"
            black -> return "black"
            blanchedalmond -> return "blanchedalmond"
            blue -> return "blue"
            blueviolet -> return "blueviolet"
            brown -> return "brown"
            burlywood -> return "burlywood"
            cadetblue -> return "cadetblue"
            chartreuse -> return "chartreuse"
            chocolate -> return "chocolate"
            coral -> return "coral"
            cornflowerblue -> return "cornflowerblue"
            cornsilk -> return "cornsilk"
            crimson -> return "crimson"
            cyan -> return "cyan"
            darkblue -> return "darkblue"
            darkcyan -> return "darkcyan"
            darkgoldenrod -> return "darkgoldenrod"
            darkgray -> return "darkgray"
            darkgreen -> return "darkgreen"
            darkgrey -> return "darkgrey"
            darkkhaki -> return "darkkhaki"
            darkmagenta -> return "darkmagenta"
            darkolivegreen -> return "darkolivegreen"
            darkorange -> return "darkorange"
            darkorchid -> return "darkorchid"
            darkred -> return "darkred"
            darksalmon -> return "darksalmon"
            darkseagreen -> return "darkseagreen"
            darkslateblue -> return "darkslateblue"
            darkslategray -> return "darkslategray"
            darkslategrey -> return "darkslategrey"
            darkturquoise -> return "darkturquoise"
            darkviolet -> return "darkviolet"
            deeppink -> return "deeppink"
            deepskyblue -> return "deepskyblue"
            dimgray -> return "dimgray"
            dimgrey -> return "dimgrey"
            dodgerblue -> return "dodgerblue"
            firebrick -> return "firebrick"
            floralwhite -> return "floralwhite"
            forestgreen -> return "forestgreen"
            fuchsia -> return "fuchsia"
            gainsboro -> return "gainsboro"
            ghostwhite -> return "ghostwhite"
            gold -> return "gold"
            goldenrod -> return "goldenrod"
            gray -> return "gray"
            grey -> return "grey"
            green -> return "green"
            greenyellow -> return "greenyellow"
            honeydew -> return "honeydew"
            hotpink -> return "hotpink"
            indianred -> return "indianred"
            indigo -> return "indigo"
            ivory -> return "ivory"
            khaki -> return "khaki"
            lavender -> return "lavender"
            lavenderblush -> return "lavenderblush"
            lawngreen -> return "lawngreen"
            lemonchiffon -> return "lemonchiffon"
            lightblue -> return "lightblue"
            lightcoral -> return "lightcoral"
            lightcyan -> return "lightcyan"
            lightgoldenrodyellow -> return "lightgoldenrodyellow"
            lightgray -> return "lightgray"
            lightgreen -> return "lightgreen"
            lightgrey -> return "lightgrey"
            none -> return "black"
        }
    }


    companion object
    {
        fun ofRaw(colString: String): Color
        {
            var color: Color = Color()
            when
            {
                colString[0] == '#' -> // for hexadecimal values
                {
                    val colorHex =
                        Color.parseColor(colString.hexSixDigit()) // make sure it's six characters

//                Log.d(SvgColor::class.simpleName, "Color is: $colorHex")

                    color = Color.valueOf(colorHex)

                }
                colString.contains("rgb") -> // for both rgb types
                {
                    val cleanColor = colString.trimIndent()
                        .trimStart()
                        .trimEnd()
                        .trim()
                        .replace("\n", "")
                        .replace(" ", "")
                        .replace("rgb(", "")
                        .replace(")", "")

                    val colorPieces =
                        cleanColor.split(",")
                            .toTypedArray()

                    if (colorPieces.count() == 0)
                        return Color.valueOf(Color.BLACK)

                    var r: Float = 0f
                    var g: Float = 0f
                    var b: Float = 0f

                    if (colorPieces[0].contains('%')) // if percentage mode
                    {
                        r = colorPieces[0].replace("%", "").toFloat() / 100f
                        g = colorPieces[1].replace("%", "").toFloat() / 100f
                        b = colorPieces[2].replace("%", "").toFloat() / 100f
                    }
                    else
                    {
                        r = colorPieces[0].toFloat() / 255f
                        g = colorPieces[1].toFloat() / 255f
                        b = colorPieces[2].toFloat() / 255f
                    }

                    color = Color.valueOf(r, g, b)
                }
                else -> // for color keywords
                {
                    var fromRaw: SvgColor = black
                    for (any in values())
                    {
                        if (any.toString() == colString)
                        {
                            fromRaw = any
                            break
                        }
                    }
//                    if (values().contains())
//
//                    val fromRaw = values().first { it.toString() == colString }


                    val r = fromRaw.rgb.r
                    val g = fromRaw.rgb.g
                    val b = fromRaw.rgb.b

                    val colorHexString = java.lang.String.format("#%02x%02x%02x", r, g, b)
                    val intColor: Int = Color.parseColor(colorHexString)

                    color = Color.valueOf(intColor)
                }
            }


            return color.roundTwoDecimals()
        }
    }
}






// todo this exists in Moush
fun String.hexSixDigit() : String
{
    var value = this

    if ( this.length == 4)
    {
        value =
            this.replace("#([0-9a-fA-F])([0-9a-fA-F])([0-9a-fA-F])".toRegex(),
                         "#$1$1$2$2$3$3")
    }
    return value
}
