package com.oddinstitute.crossplatformsvgparser.objects

enum class ObjectType(val text: String)
{
    POLY("poly"), // sharp corners
    PATH("path"), // with cubic beziers only


    TEXT("text"), //
    IMAGE("image")  // bitmaps
}