import Foundation
import shared


//extension Array<Float>
//{
//    mutating func toKotlinFloatArray() -> Array<Float>
//    {
//
//    }
//}
func floatArryToKotlinFloatArray (input: Array<Float>) -> KotlinFloatArray
{
    
    let k = KotlinFloatArray(size: Int32(input.count))
    
    for i in 0..<input.count
    {
        k.set(index: Int32(i), value: input[i])
    }
    
    return k
}


extension String {
    
    var isNumber: Bool {
            return !isEmpty && rangeOfCharacter(from: CharacterSet.decimalDigits.inverted) == nil
        }
    
    mutating func toFloatArray() -> Array<Float> {
        
        var outList : Array<Float> = []

        let cleanText = self.replacingOccurrences(of: " ", with: ",")
        let pieces = cleanText.split(separator: ",")
        

        
        for any in pieces
        {
            if (String(any).isNumber)
            {
                outList.append((String(any) as NSString).floatValue)
            }
        }

        return outList
        
    }
}
