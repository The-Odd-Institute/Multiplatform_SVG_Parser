import Foundation
import shared

extension SvgReader
{
    func readUseTag (definedTags: Array<Tag>,
                     attributes attributeDict: [String : String]) -> UseTag
    {
        let useTag = UseTag(definedTags: definedTags as! NSMutableArray)

        useTag.attributes = self.readAttributes(attributeDict)
        
        
        
        // POLYGON
        if let it = attributeDict["x"] { useTag.x = (it as NSString).floatValue  }
        if let it = attributeDict["y"] { useTag.y = (it as NSString).floatValue  }
        if let it = attributeDict["width"] { useTag.width = (it as NSString).floatValue  }
        if let it = attributeDict["height"] { useTag.height = (it as NSString).floatValue  }
        if let it = attributeDict["href"] { useTag.href = it  }
        
        return useTag
    }
}
