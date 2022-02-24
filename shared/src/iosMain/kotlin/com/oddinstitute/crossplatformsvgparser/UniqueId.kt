package com.oddinstitute.crossplatformsvgparser

import platform.Foundation.NSUUID

actual class UniqueId actual constructor()
{
    actual var id: String = NSUUID.toString()
}