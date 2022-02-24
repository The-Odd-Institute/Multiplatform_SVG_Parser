import Foundation
import SwiftUI
import shared



extension Path
{
    mutating func lineToPoint(point: MyVector2) {
        
        self.addLine(to: CGPoint(x: CGFloat(point.x),
                                 y: CGFloat(point.y)))
    }
    
    mutating func moveToPoint(point: MyVector2) {
        self.move(to: CGPoint(x: CGFloat(point.x),
                              y: CGFloat(point.y)))
    }

    mutating func cubicToCpCpPoint(cp1: MyVector2, cp2: MyVector2, point: MyVector2) {
            
            self.addCurve(to: CGPoint(x: CGFloat(point.x),
                                      y: CGFloat(point.y)),
                          control1: CGPoint(x: CGFloat(cp1.x),
                                            y: CGFloat(cp1.y)),
                          control2: CGPoint(x: CGFloat(cp2.x),
                                            y: CGFloat(cp2.y)))
            
    }
}

