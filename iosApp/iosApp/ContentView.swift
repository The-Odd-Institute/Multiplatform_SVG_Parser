import SwiftUI
import shared


struct PathSandbox: View {
    var body: some View {
        Path { path in
            path.move(to: CGPoint(x: 200, y: 0))
            path.addLine(to: CGPoint(x: 200, y: 200))
            path.addLine(to: CGPoint(x: 0, y: 200))
            path.addLine(to: CGPoint(x: 0, y: 0))
            
            
            let myVec = MyVector2 (x: 12, y: 600)
            path.lineToPoint(point: myVec)
            
            path.closeSubpath()
        }
        
        //            .stroke() // Stroke, without is fill
    }
}


struct MySquare: SwiftUI.Shape {
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



struct LetterB: SwiftUI.Shape {
    
    func path(in rect: CGRect) -> Path {
        Path { path in
            path.move(to: CGPoint(x: rect.size.width/2, y: 0))
            path.addLine(to: CGPoint(x: 0, y: 0))
            path.addLine(to: CGPoint(x: 0, y: rect.size.width/2))
            path.addLine(to: CGPoint(x: rect.size.width/2, y: rect.size.width/2))
            path.move(to: CGPoint(x: 0, y: rect.size.width/2))
            path.addLine(to: CGPoint(x: 0, y: rect.size.width))
            path.addLine(to: CGPoint(x: rect.size.width/2, y: rect.size.width))
            path.addArc(center: CGPoint(x: rect.size.width/2, y: rect.size.width*(3/4)), radius: rect.size.width/4, startAngle: .degrees(90), endAngle: .degrees(270), clockwise: true)
            path.addArc(center: CGPoint(x: rect.size.width/2, y: rect.size.width/4), radius: rect.size.width/4, startAngle: .degrees(90), endAngle: .degrees(270), clockwise: true)
        }
    }
}



struct BBLogo: View {
    var body: some View {
        ZStack {
            RoundedRectangle(cornerRadius: 20)
                .frame(width: 200, height: 200)
            LetterB()
                .stroke(lineWidth: 12)
                .foregroundColor(.white)
                .frame(width: 100, height: 100)
            LetterB()
                .stroke(lineWidth: 12)
                .foregroundColor(.white)
                .frame(width: 100, height: 100)
                .offset(x: 40)
        }
    }
}


struct Raindrop: SwiftUI.Shape {
    
    func path(in rect: CGRect) -> Path {
        Path { path in
            path.move(to: CGPoint(x: rect.size.width/2, y: 0))
            path.addQuadCurve(to: CGPoint(x: rect.size.width/2, y: rect.size.height), control: CGPoint(x: rect.size.width, y: rect.size.height))
            path.addQuadCurve(to: CGPoint(x: rect.size.width/2, y: 0), control: CGPoint(x: 0, y: rect.size.height))
        }
    }
}

struct ContentView: View {
    let greet = Greeting().greeting()
    
    var body: some View {
        
                    PathSandbox()
        
        //        MySquare().frame(width: 300, height: 300)
        
        //        BBLogo()
        
        Raindrop ()
            .fill(LinearGradient(gradient: Gradient(colors: [.white, .blue]), startPoint: .topLeading, endPoint: .bottom))
            .frame(width: 200, height: 200)
        //            .stroke(lineWidth: 4)
        //            .frame(width: 200, height: 200)
        
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
