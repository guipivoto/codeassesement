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
    implementation(AndroidX.fragment)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.recyclerView)
    implementation(AndroidX.constraintLayout)
    implementation(ThirdParty.picasso)

    implementation(project(":features:navigation"))
    implementation(project(":models"))
    implementation(project(":repository"))

    testImplementation(Tests.rules)
    testImplementation(Tests.junit)
    testImplementation(Tests.extension)
    testImplementation(Tests.robolectric)
    testImplementation(Tests.core)
    testImplementation(Tests.espresso)
    testImplementation(Tests.mockito)
    testImplementation(Tests.mockitoKotlin)
    testImplementation(Dagger.hiltTesting)
    testImplementation(AndroidX.fragmentTesting)
    testImplementation(project(":test"))
    kaptTest(Dagger.hiltKaptCompiler)
}
