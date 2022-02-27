import Foundation
import SwiftUI
import shared


class Utils
{
    class func myColorToArgb(myColor: MyColor) -> Color
    {
        let color = Color(Color.RGBColorSpace.sRGB,
                          red: Double(myColor.r),
                          green: Double(myColor.g),
                          blue: Double(myColor.b),
                          opacity: Double(myColor.a))
        
        return color
    }
    
    
    
    class func svgLineJoinToType(text: String) -> CGLineJoin
    {
        let stringToType = ["round" : CGLineJoin.round,
                            "bevel" : CGLineJoin.bevel,
                            "miter" : CGLineJoin.miter]
        
        if let it = stringToType[text] {
            return it
        }
        
        return CGLineJoin.miter
    }
    
    
    class func svgLineCapToType(text: String) -> CGLineCap
    {
        let stringToType = ["round" : CGLineCap.round,
                            "bevel" : CGLineCap.butt,
                            "miter" : CGLineCap.square]
        
        if let it = stringToType[text] {
            return it
        }
        
        return CGLineCap.butt
    }
    
    class func svgFillRuleToType(text: String) -> Bool
    {
        // When isEOFilled is false, the style uses the non-zero winding number rule.
        
        
        
        let stringToType = ["nonzero" : false,
                            "evenodd" : true]
        
        if let it = stringToType[text] {
            return it
        }
        
        return false
        
    }
}
