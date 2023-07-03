pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "CodeAssessment"

include(
    ":app",
    ":core",
    ":features:details",
    ":features:home",
    ":features:navigation",
    ":models",
    ":repository",
    ":test"
)
