import SwiftUI
import shared


//struct MyView: View {
//    var body: some View {
//
//        Text("Hello, world!")
//            .padding()
//    }
//}

struct DrawView: View {
    
    var artwork: Artwork
    
    func getStyle () -> StrokeStyle
    {
        var style = StrokeStyle()
        style.lineCap = .butt
        style.lineWidth = 20
        
        return style
    }
    
    
//    func styleFillPaint(obj: Object) -> FillStyle
//    {
//        var style = FillStyle ()
//
//        if let it = obj.shapeAttr.fillColor {
//
//        }
//
//        // style.isEOFilled = obj.shapeAttr.
//
//        obj.shapeAttr.filColorApplied?.let {
//            paint.color = Utils.myColorToArgb(it)
//        }
//    }
//
    
    
    var body: some View {
        
        
        

        for obj in artwork.objects {
            (obj as! Object).makePath()
            
            
            if ((obj as! Object).shapeAttr.fillColor != nil)
            {
                // there is fill color
                
                // here set the style
                // styleFillPaint(obj)
                // and draw the path
                // it.makeCgPath()
            }
            
            if ((obj as! Object).shapeAttr.strokeWidth > 0)
            {
                // here set the stroke style
               //  styleStrokePaint(obj)
                
                // and draw stroke path
                // it.makeCgPath()
            }
            
            
        }
        
        
        
        return ObjectShape(artwork: artwork)
            .stroke(Color.orange, style: getStyle())
    }
}


//right now, we are mainly in the Draw View



struct ObjectShape: Shape
{
    var artwork: Artwork?
    
    func path(in rect: CGRect) -> Path {
        
 
        
        
        
        var path = Path()
        path.move(to: CGPoint(x: rect.size.width, y: 0))
        path.addLine(to: CGPoint(x: rect.size.width, y: rect.size.width))
        path.addLine(to: CGPoint(x: 0, y: rect.size.width))
        path.addLine(to: CGPoint(x: 0, y: 0))
        path.closeSubpath()
        
        
        
        return path
    }
}

