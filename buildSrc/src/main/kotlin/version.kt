import org.gradle.api.JavaVersion

object Plugins {

    const val android = "com.android.application"
    const val androidVersion = "7.4.2"

    const val androidLibrary = "com.android.library"
    const val androidLibraryVersion = "7.4.2"

    const val kotlinAndroid = "org.jetbrains.kotlin.android"
    const val kotlinAndroidVersion = "1.8.10"

    const val parcelize = "org.jetbrains.kotlin.plugin.parcelize"
    const val parcelizeVersion = "1.8.10"

    const val hiltDagger = "com.google.dagger.hilt.android"
    const val hiltDaggerVersion = "2.45"

    const val kapt = "kapt"
}

object App {
    const val compileSdk = 33
    const val minSdk = 26
    const val targetSdk = 33
    private const val majorVersion = 1
    private const val minorVersion = 0
    private const val patchVersion = 0
    const val versionCode = majorVersion * 1000 + minorVersion * 100 + patchVersion
    const val versionName = "$majorVersion.$minorVersion.$patchVersion"
}


object Java {
    val version = JavaVersion.VERSION_11
}

object Kotlin {
    private const val version = "1.8.10"
    const val test = "org.jetbrains.kotlin:kotlin-test:$version"

    private const val coroutineVersion = "1.6.4"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVersion"
}

object Android {
    const val material = "com.google.android.material:material:1.8.0"
}

object AndroidX {
    const val core = "androidx.core:core-ktx:1.9.0"
    const val appCompat = "androidx.appcompat:appcompat:1.6.1"
    const val recyclerView = "androidx.recyclerview:recyclerview:1.3.0"
    const val fragment = "androidx.fragment:fragment-ktx:1.5.5"
    const val fragmentTesting = "androidx.fragment:fragment-testing:1.5.5"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.4"
    const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:2.6.0"
}

object Dagger {
    private const val version = Plugins.hiltDaggerVersion
    const val hiltAndroid = "com.google.dagger:hilt-android:$version"
    const val hiltTesting = "com.google.dagger:hilt-android-testing:$version"
    const val hiltKaptCompiler = "com.google.dagger:hilt-android-compiler:$version"
    const val hiltNavigation = "androidx.hilt:hilt-navigation-fragment:1.0.0"
    const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:$version"
}

object ThirdParty {
    const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
    const val retrofit2Gson = "com.squareup.retrofit2:converter-gson:2.9.0"
    const val gson = "com.google.code.gson:gson:2.9.0"
    const val picasso = "com.squareup.picasso:picasso:2.8"
}

object Tests {
    const val junit = "junit:junit:4.13.2"
    const val mockito = "org.mockito:mockito-core:5.2.0"
    const val mockitoKotlin = "org.mockito.kotlin:mockito-kotlin:4.1.0"
    const val robolectric = "org.robolectric:robolectric:4.10.3"
    const val runner = "androidx.test:runner:1.5.2"
    const val core = "androidx.test:core-ktx:1.5.0"
    const val rules = "androidx.test:rules:1.5.0"
    const val extension = "androidx.test.ext:junit:1.1.5"
    const val espresso = "androidx.test.espresso:espresso-core:3.5.1"
}
