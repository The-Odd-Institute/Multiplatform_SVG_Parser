import Foundation
import shared
import SwiftUI
import CoreGraphics

func makeCgPath (myPath: MyPath2) ->  Path
{
    var path = Path ()
    
    myPath.pathData.pieces.forEach { p in
        
        let piece = p as! PathPiece
        
        
        switch(piece.pathPieceType)
        {
        case MyPathPieceType.move:
            path.moveToPoint(point: piece.v)
            
        case MyPathPieceType.line:
            path.lineToPoint(point: piece.v)
            
        case MyPathPieceType.curve:
            if let o = piece.o {
                if let i = piece.i {
                    path.cubicToCpCpPoint(point: piece.v,
                                          cp1: o,
                                          cp2: i)
                }
            }
            
        default: print ("weird path type)")
        }
    }
    
    
    if (myPath.pathData.closed)
    {
        path.closeSubpath()
    }
    
    
    
    //    this.pathData.fillRule?.let {
    //        path.fillType = Utils.svgFillRuleToType(it)
    //    }
    
    return path
}

