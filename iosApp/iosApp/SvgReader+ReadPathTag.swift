import Foundation
import shared

extension SvgReader
{
    
    func readPathTag(attributes attributeDict: [String : String]) -> PathTag
    {
        
        
        let pathTag = PathTag()
        pathTag.attributes = self.readAttributes(attributeDict)
        
        
        
        
        // PATH
        
        if let it = attributeDict["d"] { pathTag.d = it  }
        if let it = attributeDict["rx"] { pathTag.rx = KotlinFloat(value: (it as NSString).floatValue)  }
        if let it = attributeDict["ry"] { pathTag.ry = KotlinFloat(value: (it as NSString).floatValue)  }
        if let it = attributeDict["cx"] { pathTag.cx = (it as NSString).floatValue }
        if let it = attributeDict["cy"] { pathTag.cy = (it as NSString).floatValue }
        if let it = attributeDict["r"] { pathTag.r = KotlinFloat(value: (it as NSString).floatValue) }
        

        return pathTag
    }
}
