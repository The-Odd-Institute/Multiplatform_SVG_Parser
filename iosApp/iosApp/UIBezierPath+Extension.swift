import UIKit
import shared


extension UIBezierPath {
    
    
    
    class func shapeMaker2 (myPath: MyPath2) -> UIBezierPath
    {
        let path = myPath.makeCgPath()
        
        
//        let shape = UIBezierPath()
//        shape.move(to: CGPoint(x: 0, y: 0))
//        shape.addLine(to: CGPoint(x: 0, y: 50))
//        shape.addLine(to: CGPoint(x: 50, y: 50))
//
//        shape.close()
        return path
    }
    
    
    class func shapeMaker () -> UIBezierPath
    {
        
        let shape = UIBezierPath()
        shape.move(to: CGPoint(x: 0, y: 0))
        shape.addLine(to: CGPoint(x: 0, y: 50))
        shape.addLine(to: CGPoint(x: 50, y: 50))

        shape.close()
        return shape
    }
}

extension UIBezierPath {
    static func calculateBounds(paths: [UIBezierPath]) -> CGRect {
        let myPaths = UIBezierPath()
        for path in paths {
            myPaths.append(path)
        }
        return (myPaths.bounds)
    }
    
    
    
    
    
    
    func ctLogo3 () -> UIBezierPath
    {
        let shape = UIBezierPath()
        shape.move(to: CGPoint(x: 0, y: 0))
        shape.addLine(to: CGPoint(x: 0, y: 50))
        shape.addLine(to: CGPoint(x: 50, y: 50))
        
        shape.close()
        return shape
    }
    
    
    static var ctLogo1: UIBezierPath
    {
        let shape = UIBezierPath()
        shape.move(to: CGPoint(x: 100, y: 100))
        shape.addLine(to: CGPoint(x: 100, y: 200))
        shape.addLine(to: CGPoint(x: 200, y: 200))
        
        shape.close()
        return shape
        
    }
    
    static var ctLogo2: UIBezierPath
    {
        let shape = UIBezierPath()
        shape.move(to: CGPoint(x: 275, y: 175))
        shape.addLine(to: CGPoint(x: 275, y: 25))
        shape.addLine(to: CGPoint(x: 275, y: 175))
        
        shape.close()
        return shape
        
    }
    
}
