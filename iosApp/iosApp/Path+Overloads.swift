import Foundation
import SwiftUI
import shared

extension UIBezierPath
{
    func lineToPoint(point: MyVector2) {
        
        self.addLine(to: CGPoint(x: CGFloat(point.x),
                                 y: CGFloat(point.y)))
    }
    
    func moveToPoint(point: MyVector2) {
        self.move(to: CGPoint(x: CGFloat(point.x),
                              y: CGFloat(point.y)))
    }
    
    func cubicToCpCpPoint(point: MyVector2, cp1: MyVector2, cp2: MyVector2) {
        
        self.addCurve(to: CGPoint(x: CGFloat(point.x),
                                  y: CGFloat(point.y)),
                      controlPoint1: CGPoint(x: CGFloat(cp1.x),
                                             y: CGFloat(cp1.y)),
                      controlPoint2: CGPoint(x: CGFloat(cp2.x),
                                        y: CGFloat(cp2.y)))
    }
}



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
    
    mutating func cubicToCpCpPoint(point: MyVector2, cp1: MyVector2, cp2: MyVector2) {
        
        self.addCurve(to: CGPoint(x: CGFloat(point.x),
                                  y: CGFloat(point.y)),
                      control1: CGPoint(x: CGFloat(cp1.x),
                                        y: CGFloat(cp1.y)),
                      control2: CGPoint(x: CGFloat(cp2.x),
                                        y: CGFloat(cp2.y)))
    }
}
