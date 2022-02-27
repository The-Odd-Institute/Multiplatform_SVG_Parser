import SwiftUI
import shared


struct MyObjectShape: Hashable
{
    var object: Object
    var shape: UIBezierPath
}



struct ContentView: View {
    
    func getArtwork () -> Artwork
    {
        return parseIt().1!
    }
    
    
    let ctColor1 = UIColor.fromHexString("29ABE2")
    
    
    
    
    var objectsAndShapes: [MyObjectShape]
    {
        var outArr: [MyObjectShape] = []
        
        if let artwork = parseIt().1 {
            
            artwork.objects.forEach { obj in
                let object = obj as! Object
                
                object.makePath()
                
                if let path = object.path?.makeCgPath() {
                    
                    let my = MyObjectShape(object: object, shape: path)
                    outArr.append(my)
                }
                
            }
        }
        
        
        
        //        arr = [.ctLogo1, .ctLogo2]
        
        return outArr
    }
    
    let pathBounds = CGRect(x: 0, y: 0, width: 512, height: 512)//  UIBezierPath.calculateBounds(paths: [.ctLogo2, .ctLogo1])
    
    
    var names = ["amir", "Steve"]
    
    let colors: [Color] = [.red, .green, .blue]
    
    var body: some View {
        
        
        
        ZStack
        {
            ForEach(objectsAndShapes, id: \.self) { shapeObj in
                
                let obj = shapeObj.object
                let shape = shapeObj.shape
                
                
                if let fillC = obj.shapeAttr.fillColor {
                    ShapeView(bezier: shape,
                              pathBounds: pathBounds)
                        .fill(Color(UIColor.fromMyColor(fillC)))
                }
                
                if (obj.shapeAttr.strokeWidth > 0)
                {
                    if let strokeC = obj.shapeAttr.strokeColor
                    {
                        // stroke
                        ShapeView(bezier: shape,
                                  pathBounds: pathBounds)
                            .stroke(Color(UIColor.fromMyColor(strokeC)),
                                    lineWidth: CGFloat(obj.shapeAttr.strokeWidth))
                    }
                }
            }
        }
        .frame(width: 768, height: 768, alignment: .topLeading)

//        .padding([.top, .leading], 10)
        
        
        
        //        BadgeBackground (artwork: getArtwork())
        
        
        
        
        
    }
}

extension ContentView
{
    func parseIt () -> (String, Artwork?)
    {
        let myParser = SvgReader()
        
        if let path = Bundle.main.url(forResource: "mysvg",
                                      withExtension: "xml") {
            if let xmlParser = XMLParser(contentsOf: path) {
                xmlParser.delegate = myParser
                xmlParser.parse()
                
                //                    print("found: \(myParser.data.artwork.objects.count)")
                //                    print("its: \((myParser.data.artwork.objects.firstObject as! Object).type)")
                //                    print("it has: \((myParser.data.artwork.objects.firstObject as! Object).segments.count)")
                //                    print("first one is: \(((myParser.data.artwork.objects.firstObject as! Object).segments.firstObject as! Segment).type)")
                
                
                return ("success", myParser.data.artwork)
            }
        }
        
        return ("Failed", nil)
    }
    
}
