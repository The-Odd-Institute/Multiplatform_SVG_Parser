package com.oddinstitute.crossplatformsvgparser

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.*


// FIXME - This serialization shoudl be for MyVector2
// serialization of PointF
object MyVector2AsStringSerializer : KSerializer<MyVector2>
{
    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor("Point") {
            element<Float>("x")
            element<Float>("y")
        }

    override fun serialize(encoder: Encoder, value: MyVector2) =
        encoder.encodeStructure(descriptor) {
            encodeFloatElement(descriptor, 0, value.x)
            encodeFloatElement(descriptor, 1, value.y)
        }

    override fun deserialize(decoder: Decoder): MyVector2 =
        decoder.decodeStructure(descriptor)
        {
            var x = -1f
            var y = -1f
            while (true)
            {
                when (val index = decodeElementIndex(descriptor))
                {
                    0 -> x = decodeFloatElement(descriptor, 0)
                    1 -> y = decodeFloatElement(descriptor, 1)
                    CompositeDecoder.DECODE_DONE -> break
                    else -> error("Unexpected index: $index")
                }
            }
            MyVector2(x, y)
        }
}