plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    kotlin(Plugins.kapt)
    id(Plugins.hiltDagger)
}

android {
    namespace = "com.pivoto.codeassessment.core"
}

dependencies {
    implementation(AndroidX.lifecycle)
    implementation(AndroidX.fragment)
}
