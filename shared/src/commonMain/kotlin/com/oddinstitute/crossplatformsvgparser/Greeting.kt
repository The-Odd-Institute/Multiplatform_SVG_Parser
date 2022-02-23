package com.oddinstitute.crossplatformsvgparser

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}