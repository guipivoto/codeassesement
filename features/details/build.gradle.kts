plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    kotlin(Plugins.kapt)
    id(Plugins.hiltDagger)
}

android {
    namespace = "com.pivoto.codeassessment.features.home"
}

dependencies {
    implementation(ThirdParty.picasso)
    implementation(AndroidX.constraintLayout)
    implementation(project(":models"))
    implementation(project(":features:navigation"))
}
