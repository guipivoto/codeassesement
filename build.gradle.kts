import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.LibraryPlugin
import dagger.hilt.android.plugin.HiltGradlePlugin
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id (Plugins.android) version Plugins.androidVersion apply false
    id (Plugins.androidLibrary) version Plugins.androidLibraryVersion apply false
    id (Plugins.kotlinAndroid) version Plugins.kotlinAndroidVersion apply false
    id (Plugins.parcelize) version Plugins.parcelizeVersion apply false
    id (Plugins.hiltDagger) version Plugins.hiltDaggerVersion apply false
}

subprojects {
    project.plugins.applyBaseConfig(project)
}

fun PluginContainer.applyBaseConfig(project: Project) {
    whenPluginAdded {
        when (this) {
            is AppPlugin -> {
                // Add common dependencies for the Android Application plugin
                project.extensions.getByType<AppExtension>().apply { baseConfig() }
                project.dependencies.apply {
                    if (":core" != project.path) {
                        // Add base core module to any other module except itself
                        add("implementation", project(":core"))
                    }
                }
            }
            is LibraryPlugin -> {
                // Add common dependencies for any module using Android Library plugin
                project.extensions.getByType<LibraryExtension>().apply { baseConfig() }
                project.dependencies.apply {
                    add("implementation", Kotlin.coroutinesAndroid)
                    if (":core" != project.path) {
                        // Add base core module to any other module except itself
                        add("implementation", project(":core"))
                    }
                }
            }
            is HiltGradlePlugin -> {
                // Add common dependencies for any module using Dagger
                project.dependencies.apply {
                    add("kapt", Dagger.hiltAndroidCompiler)
                    add("implementation", Dagger.hiltAndroid)
                    add("implementation", Dagger.hiltNavigation)
                }
            }
        }
    }
}

fun BaseExtension.baseConfig() {

    compileSdkVersion(App.compileSdk)

    defaultConfig.apply {
        minSdk = App.minSdk
        targetSdk = App.targetSdk
        testInstrumentationRunner = "com.pivoto.codeassessment.test.TestRunner"
    }

    testOptions.apply {
        unitTests.apply {
            isIncludeAndroidResources = true
        }
    }

    buildTypes.apply {
        getByName("debug") {
            isMinifyEnabled = false
            isShrinkResources = false
        }

        getByName("release") {
            isMinifyEnabled = false
        }
    }

    buildFeatures.apply {
        viewBinding = true
        buildConfig = false
    }

    compileOptions.apply {
        sourceCompatibility = Java.version
        targetCompatibility = Java.version
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = Java.version.toString()
        }
    }
}
