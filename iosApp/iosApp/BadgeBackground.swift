import Foundation
import SwiftUI
import shared


//struct MyShape: Shape {
//    
//    var object: Object
//    
//    init(obj: Object) {
//            self.object = obj
//        
//        object.makePath()
//
//            // Do whatever you need
//        }
//    
//    
//    func path(in rect: CGRect) -> Path {
//        
//        
//        if let myPath = object.path {
//            return makeCgPath(myPath: myPath)
//    }
//    }
//}


struct ObjectDrawView: View {
    
    var object: Object
    
    var body: some View {
        
        GeometryReader { geometry in
            Path { path in
                    object.makePath()
                    
                    if let myPath = object.path {
                        path = makeCgPath(myPath: myPath)
                }
                
                print ("path is ObjectDrawView: \(path)")
            }
            .fill(.red)
        }
    }
}



struct BadgeBackground: View {
    
    var artwork: Artwork
    
    var body: some View {
        
        GeometryReader { geometry in
            Path { path in
                artwork.objects.forEach { obj in
                    let object = obj as! Object
                    
                    object.makePath()
                    
                    
                    if let myPath = object.path {
                        path = makeCgPath(myPath: myPath)
                    }
                }
                
                print ("path is: \(path)")
            }
            .fill(.red)
        }
    }
}



/*
 
 struct BadgeBackground: View {
     
     var artwork: Artwork
     
     var body: some View {
         
         GeometryReader { geometry in
             Path { path in
                 artwork.objects.forEach { obj in
                     let object = obj as! Object
                     
                     object.makePath()
                     
                     
                     if let myPath = object.path {
                         path = makeCgPath(myPath: myPath)
                     }
                 }
                 
                 print ("path is: \(path)")
             }
             .fill(.red)
         }
     }
     
     static let gradientStart = Color(red: 239.0 / 255, green: 120.0 / 255, blue: 221.0 / 255)
     
     static let gradientEnd = Color(red: 239.0 / 255, green: 172.0 / 255, blue: 120.0 / 255)
 }


 */
