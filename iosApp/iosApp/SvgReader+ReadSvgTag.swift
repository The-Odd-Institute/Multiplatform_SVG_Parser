import Foundation
import shared


extension SvgReader
{
    func readSvgTag(attributes attributeDict: [String : String]) -> SvgTag
    {
        
        if let it = attributeDict["viewBox"] {
            return SvgTag (viewBoxString: it)
        }
       
        return SvgTag (viewBoxString: "")       
    }
}
