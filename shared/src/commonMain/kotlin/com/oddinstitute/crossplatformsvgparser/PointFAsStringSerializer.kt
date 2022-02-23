package com.oddinstitute.crossplatformsvgparser

import android.graphics.PointF
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.*

// serialization of PointF
object PointFAsStringSerializer : KSerializer<PointF>
{
    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor("Point") {
            element<Float>("x")
            element<Float>("y")
        }

    override fun serialize(encoder: Encoder, value: PointF) =
        encoder.encodeStructure(descriptor) {
            encodeFloatElement(descriptor, 0, value.x)
            encodeFloatElement(descriptor, 1, value.y)
        }

    override fun deserialize(decoder: Decoder): PointF =
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
            PointF(x, y)
        }
}