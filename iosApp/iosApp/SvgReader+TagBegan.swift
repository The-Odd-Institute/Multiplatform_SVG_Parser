import Foundation
import shared


extension SvgReader
{
    func tagBegan(_ attributeDict: [String : String])
    {
        switch (data.curTagName)
        {
        case "polyline", "line", "polygon", "rect":
            
            let polytag = self.readPolyTag(closed: data.curTagName == "polygon" || data.curTagName == "rect",
                                           attributes: attributeDict)
            
            if (data.definitionState)
            {
                data.definitions.add(polytag)
            }
            else
            {
                if let it = SVG().assembleTag(anyTag: polytag,
                                              currentGroups: data.currentGroups,
                                              styles: data.styles,
                                              scaleFactor: data.scaleFactor,
                                              viewBoxOffset: data.viewBoxOffset)
                { data.artwork.objects.add(it) }
            }
            
        case "path", "circle", "ellipse" :
            
            let pathTag = self.readPathTag(attributes: attributeDict)
            
            if (data.definitionState)
            {
                data.definitions.add(pathTag)
            }
            else
            {
                if let it = SVG().assembleTag(anyTag: pathTag,
                                              currentGroups: data.currentGroups,
                                              styles: data.styles,
                                              scaleFactor: data.scaleFactor,
                                              viewBoxOffset: data.viewBoxOffset)
                { data.artwork.objects.add(it) }
                
            }
            
        case "use":
            let useTag = self.readUseTag(definedTags: data.definitions as! Array<Tag>,
                                         attributes: attributeDict)
            let resultTag = useTag.resultTag()
            
            if let it = SVG().assembleTag(anyTag: resultTag,
                                          currentGroups: data.currentGroups,
                                          styles: data.styles,
                                          scaleFactor: data.scaleFactor,
                                          viewBoxOffset: data.viewBoxOffset)
            { data.artwork.objects.add(it) }
            
        case "g":
            let tag = Tag ()
            tag.attributes = self.readAttributes(attributeDict)
            data.currentGroups.add(tag)
            
        case "svg":
            let svgTag = self.readSvgTag(attributes: attributeDict)
            let decodedViewBox = svgTag.decode()
            data.scaleFactor = decodedViewBox.second as! Float
            data.viewBoxOffset = decodedViewBox.first ?? MyVector2(x: 0,y: 0)
            
        default: print("Began parsing unknown tag")
        }
    }
    }

