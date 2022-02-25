import Foundation
import shared

extension SvgReader
{
    func readPolyTag(closed: Bool = false,
                     attributes attributeDict: [String : String]) -> PolyTag
    {
        let polyTag = PolyTag(closed: false)
        polyTag.attributes = self.readAttributes(attributeDict)
        polyTag.closed = closed
        
        
        
        // POLYGON
        if let it = attributeDict["points"] { polyTag.points = it  }
        
        // LINE
        if let it = attributeDict["x1"] { polyTag.x1 = KotlinFloat(value: (it as NSString).floatValue)  }
        if let it = attributeDict["y1"] { polyTag.y1 = (it as NSString).floatValue  }
        if let it = attributeDict["x2"] { polyTag.x2 = (it as NSString).floatValue  }
        if let it = attributeDict["y2"] { polyTag.y2 = (it as NSString).floatValue  }
        
        // RECT
        if let it = attributeDict["x"] { polyTag.x = (it as NSString).floatValue  }
        if let it = attributeDict["y"] { polyTag.y = (it as NSString).floatValue  }
        if let it = attributeDict["width"] { polyTag.width = KotlinFloat(value: (it as NSString).floatValue)  }
        if let it = attributeDict["height"] { polyTag.height = (it as NSString).floatValue  }
        if let it = attributeDict["rx"] { polyTag.rx = KotlinFloat(value: (it as NSString).floatValue) }
        if let it = attributeDict["ry"] { polyTag.ry = KotlinFloat(value: (it as NSString).floatValue)  }
        
        
        return polyTag
    }
}
