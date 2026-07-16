pluginManagement {
  repositories {
    gradlePluginPortal()
    mavenCentral()
  }
}

dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    mavenCentral()
    mavenLocal()
  }
}

rootProject.name = "runapi-volcengine-lip-sync-java"

include("runapi-core")
include("runapi-volcengine-lip-sync")
