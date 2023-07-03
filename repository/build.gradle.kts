plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    kotlin(Plugins.kapt)
    id(Plugins.hiltDagger)
}

android {
    namespace = "com.pivoto.codeassessment.repository"
}

dependencies {
    implementation(project(":models"))
    implementation(ThirdParty.gson)
    implementation(ThirdParty.retrofit)
    implementation(ThirdParty.retrofit2Gson)

    testImplementation(Tests.rules)
    testImplementation(Tests.junit)
    testImplementation(Tests.extension)
    testImplementation(Tests.core)
    testImplementation(Tests.espresso)
    testImplementation(Tests.mockito)
    testImplementation(Tests.mockitoKotlin)
}
