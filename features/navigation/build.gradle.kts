plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    kotlin(Plugins.kapt)
    id(Plugins.hiltDagger)
    id(Plugins.parcelize)
}

android {
    namespace = "com.pivoto.codeassessment.features.navigation"
}

dependencies {
    implementation(project(":models"))
}
