# Multiplatform_SVG_Parser

So, I spent two weeks programming an SVG reader in Kotlin. Then, spend two days to learn and use KMM (Kotlin Mobile Multiplatform) to port the same code to be used in iOS. Here is what I learned:

KMM certainly cuts down on the amount of code. Significantly! It is most useful for situations where a library might constantly change. While you still do your UI separately, you can make sure that you don't have to code the logic twice.

Its main advantage is the IDE. Android Studio is an unbelievable IDE. Like the features, debugging, customization, and scale that it provides have no parallel (at least not that I know of).

Another main advantage is the strength of Kotlin itself and the design of the KMM. Whatever you code, you nearly don't have to port, export, or pretty much do anything. You open the iOS app, and the Kotlin code is readily compiled for iOS use.

Now, to the disadvantages, I'd say right now, you can use it for production, but for me, two things have to be resolved before it's production-ready.

a) there should be support for companion objects. (which I am really surprised there isn't. Since Swift support this, I would think that unnamed companion objects would be one of the easier features to translate)
b) high-level functions should be supported. (again, Swift supports this, so I think it shouldn't be all that difficult)

Finally, if you are thinking of using KMM, what I learned is this: you need to code in parallel. You can spend a few weeks on Android and then come to fix your iOS. This way, you will strategize things properly right in advance.


![alt text](https://github.com/The-Odd-Institute/Multiplatform_SVG_Parser/blob/main/svg_parser.png?raw=true)
![alt text](https://github.com/The-Odd-Institute/Multiplatform_SVG_Parser/blob/main/color_picker.png?raw=true)

