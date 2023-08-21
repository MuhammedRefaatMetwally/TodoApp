import java.net.URI

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
        maven {
            url = URI("https://jitpack.io")
        }
        mavenCentral()
    }
}

rootProject.name = "TodosAppC38Online"
include(":app")
