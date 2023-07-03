plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.parcelize)
    kotlin(Plugins.kapt)
    id(Plugins.hiltDagger)
}

android {
    namespace = "com.pivoto.codeassessment.test"
}

dependencies {

    implementation(AndroidX.appCompat)
    implementation(Tests.runner)
    implementation(Tests.junit)
    implementation(Dagger.hiltTesting)
}
