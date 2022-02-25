import SwiftUI
import shared


struct DrawView: View {
    
    func getStyle () -> StrokeStyle
    {
        var style = StrokeStyle()
        style.lineCap = .butt
        style.lineWidth = 20
        
        return style
    }
    
    func makePath ()
    {
        
    }
    

    
    
    
    var body: some View {
 
        
        let artwork = Artwork()

        return ArtworkShape(artwork: artwork, myX: 220)
            .stroke(Color.orange, style: getStyle())
    }
}


extension PathObj
{
    override public func makePath ()
    {
        
    }
}



struct ArtworkShape: Shape
{
    var artwork: Artwork
    var myX: Int

    
    func makeMyPath () -> Path
    {
        for obj in artwork.objects
        {
            obj.makePath()
        }
        
        
        
        var path = Path()
        path.move(to: CGPoint(x: 400, y: 0))
        path.addLine(to: CGPoint(x: 400, y: 400))
        path.addLine(to: CGPoint(x: myX, y: 400))
        path.addLine(to: CGPoint(x: 0, y: 0))
        
        path.closeSubpath()
        
        return path
    }
    
    
    
    
    func path(in rect: CGRect) -> Path {

        return makeMyPath()
    }
}

