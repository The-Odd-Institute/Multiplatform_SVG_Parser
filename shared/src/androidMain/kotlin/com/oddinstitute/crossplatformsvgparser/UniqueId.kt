package com.oddinstitute.crossplatformsvgparser

import java.util.*

actual class UniqueId actual constructor()
{
    actual var id: String = UUID.randomUUID().toString()
}