plugins {
    id (Plugins.android)
    id (Plugins.kotlinAndroid)
    kotlin(Plugins.kapt)
    id(Plugins.hiltDagger)
}

android {

    namespace = "com.pivoto.codeassessment"

    defaultConfig {
        applicationId = "com.pivoto.codeassessment"
        versionCode = App.versionCode
        versionName = App.versionName
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(AndroidX.core)
    implementation(AndroidX.appCompat)
    implementation(Android.material)
    implementation(project(":features:home"))
    implementation(project(":features:details"))
}
