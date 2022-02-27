package com.oddinstitute.crossplatformsvgparser

import com.oddinstitute.crossplatformsvgparser.to_refactor.MyColor



// FIXME
//actual fun hexToMyColor(colorStr: String): MyColor
//{
//    val hexR = colorStr.substring(1, 3)
//    val hexG = colorStr.substring(3, 5)
//    val hexB = colorStr.substring(5, 7)
//
//
//    val  r = hexR.toInt(16)
//    val  g = hexG.toInt(16)
//    val  b = hexB.toInt(16)
//
//
////    val r = Integer.valueOf(, 16)
////    val g = Integer.valueOf(colorStr.substring(3, 5), 16)
////    val b = Integer.valueOf(colorStr.substring(5, 7), 16)
//
//    return MyColor(r.toFloat() / 255f,
//                   g.toFloat() / 255f,
//                   b.toFloat() / 255f, 1f)
//
////
////    val r: Float
////    val g: Float
////    val b: Float
////    val a: Float
////
////
////    val hexR = Integer.valueOf(colorStr.substring(1, 3), 16)
////    val hexG = Integer.valueOf(colorStr.substring(3, 5), 16)
////    val hexB = Integer.valueOf(colorStr.substring(5, 7), 16)
////
////    return MyColor(r.toFloat() / 255f,
////                   g.toFloat() / 255f,
////                   b.toFloat() / 255f, 1f)
////
////
////    let scanner = Scanner(string: colorStr)
////    var hexNumber: UInt64 = 0
////    if scanner.scanHexInt64(&hexNumber) {
////    r = CGFloat((hexNumber & 0xff0000) >> 16) / 255
////    g = CGFloat((hexNumber & 0x00ff00) >> 8) / 255
////    b = CGFloat(hexNumber & 0x0000ff) / 255
////    return UIColor(red: r, green: g, blue: b, alpha: alpha)
////}
////    return UIColor(red: 0, green: 0, blue: 0, alpha: alpha)
////}
////
////
////    val r = 0f // Integer.valueOf(colorStr.substring(1, 3), 16)
////    val g = 0f // Integer.valueOf(colorStr.substring(3, 5), 16)
////    val b = 0f // Integer.valueOf(colorStr.substring(5, 7), 16)
////
////    return MyColor(r,
////                   g,
////                   b, 1f)
//}
