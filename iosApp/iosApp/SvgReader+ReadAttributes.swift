import shared
import Foundation

extension SvgReader
{
    
    func readAttributes(_ attributeDict: [String : String]) -> SvgAttributesBundle
    {
        let attr = SvgAttributesBundle()
        
        
        if let it = attributeDict["fill"] {
            if (it != "none") {
                attr.fill = SvgColor().ofRaw(colString: it)
            }
        }
        
        if let it = attributeDict["id"] {
            attr.id = it.replacingOccurrences(of: "#", with: "")
                .trimmingCharacters(in: .whitespacesAndNewlines)
                .replacingOccurrences(of: " ", with: "")
        }
        
        
        if let it = attributeDict["stroke"] {
            if (it != "none")
            {
                attr.stroke = SvgColor().ofRaw(colString: it)
                attr.strokeWidth = 1.0 // if there's a stroke color, then there's a stroke
                // in the next code, this might be updated with the actual stroke width
            }
        }
        
        if let it = attributeDict["stroke-width"] {
            attr.strokeWidth = KotlinFloat(value: (it as NSString).floatValue)

        }
        
        if let it = attributeDict["stroke-linecap"] {
            attr.strokeLineCap = it

        }
        
        if let it = attributeDict["stroke-linecap"] {
            attr.strokeLineCap = it

        }
        
        if let it = attributeDict["stroke-dasharray"] {
            attr.strokeDashArray = it
            // the SVG dasharray can have multiple entries. They seem meaningless in Android
        }

        if let it = attributeDict["transform"] {
            attr.transforms = SvgTransform().decodeTransform(text: it)
        }

        if let it = attributeDict["fill-rule"] {
            attr.fillRule = it
        }
        
        if let it = attributeDict["clip-rule"] {
            attr.clipRule = it
        }
        
        if let it = attributeDict["stroke-linejoin"] {
            attr.strokeLineJoin = it
        }
        
        if let it = attributeDict["style"] {
            attr.style = SvgStyle(sText: it)
        }
        
        if let it = attributeDict["class"] {
            attr.svgClass = it // build a class with the name that is on it
        }
                
        return attr
    }
}
