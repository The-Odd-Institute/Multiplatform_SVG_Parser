import shared
import Foundation

extension SvgReader
{
    func tagEnded()
    {
        switch (data.curTagName)
        {
        case "style":
            let styleTag = StyleTag(styleText: data.curTagText)
            self.data.styles = styleTag.decodeStyle()
        case "g":
            data.currentGroups.removeLastObject() //  activeGroup = null
        case "defs":
            data.definitionState = false
        default:
            print("weird tag ended: \(data.curTagName ?? "non existent tag name")")
        }
        
        data.curTagName = nil
    }
}
