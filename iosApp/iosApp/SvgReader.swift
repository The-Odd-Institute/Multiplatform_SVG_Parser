import Foundation
import shared


class SvgReader : NSObject, XMLParserDelegate
{
//     var element: String? = nil
     var data: SvgData = SvgData()
//
//     func parse () -> String?
//     {
//         
//         if let path = Bundle.main.url(forResource: "mysvg",
//                                       withExtension: "xml") {
//                 if let parser = XMLParser(contentsOf: path) {
//                     parser.delegate = self
//                     parser.parse()
//
//                     return "success"
//                 }
//             }
//
//
//         // MARK: something (todo as)
//         // FIXME: this (FIXME as)
//         // TODO: fix these (TODO as)
//         // todo THESE HAVE TO BE ADDED LATER ON
//         // this.makeDefaultMotionBundle()
//         // findPivot()
//
//
//         // Here, we don't return anything.
//         // the assumption is that the artwrok from data is so far populated
//
//
//         return nil
//
//     }



     func parser(_ parser: XMLParser,
                 didStartElement elementName: String,
                 namespaceURI: String?,
                 qualifiedName qName: String?,
                 attributes attributeDict: [String : String] = [:])
     {
//         element = elementName
         
         data.curTagName = elementName

         tagBegan(attributeDict)
             
//         dump(attributeDict)

//         print ("start: " + elementName)
     }

     func parser(_ parser: XMLParser,
                 didEndElement elementName: String,
                 namespaceURI: String?,
                 qualifiedName qName: String?)
     {
//         print ("end: " + elementName)
         
         
         tagEnded()


     }


     func parser(_ parser: XMLParser,
                 foundCharacters string: String)
     {
//         if element == "string" { }

         data.curTagText = string
         
         

//         print ("character: " + string)
     }


     func parser(_ parser: XMLParser,
                 foundAttributeDeclarationWithName attributeName: String,
                 forElement elementName: String,
                 type: String?,
                 defaultValue: String?) {

//         print("here")
//
//         print ("""
//         attributeName: \(attributeName) \n
//         elementName: \(elementName) \n
//         type: \(type) \n
//         defaultValue: \(defaultValue)
//         """)

     }
}

