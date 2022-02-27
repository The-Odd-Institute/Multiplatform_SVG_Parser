import Foundation
import shared
import SwiftUI




extension MyPath2
{
    func makeCgPath () -> UIBezierPath
    {
        var retPath = UIBezierPath()
        for piece in self.pathData.pieces
        {
            guard let p = piece as? PathPiece else { return retPath }
            
            switch (p.pathPieceType)
            {
            case MyPathPieceType.move:
                retPath.moveToPoint(point: p.v)
            case MyPathPieceType.line:
                retPath.lineToPoint(point: p.v)
            case MyPathPieceType.curve:
                
                if let o = p.o {
                    if let i = p.i {
                        retPath.cubicToCpCpPoint(point: p.v, cp1: o, cp2: i)
                    }
                }
            default:
                print("not sure what's this piece")
                
            }
        }
        
        if (self.pathData.closed)
        {
            retPath.close()
        }
        

        

        return retPath
    }
}




extension MyPath2
{
    var path: Path {
        
        var retPath = Path()
        for piece in self.pathData.pieces
        {
            guard let p = piece as? PathPiece else { return retPath }
            
            switch (p.pathPieceType)
            {
            case MyPathPieceType.move:
                retPath.moveToPoint(point: p.v)
            case MyPathPieceType.line:
                retPath.lineToPoint(point: p.v)
            case MyPathPieceType.curve:
                
                if let o = p.o {
                    if let i = p.i {
                        retPath.cubicToCpCpPoint(point: p.v, cp1: o, cp2: i)
                    }
                }
            default:
                print("not sure what's this piece")
                
            }
        }
        
        if (self.pathData.closed)
        {
            retPath.closeSubpath()
        }
        
        
        if let it = self.pathData.fillRule {
            
            // FIXME: This isn't really working
            
            retPath.fill(style: FillStyle.init(eoFill: Utils.svgFillRuleToType(text: it),
                                               antialiased: true))
        }
        

        return retPath
    }
}


