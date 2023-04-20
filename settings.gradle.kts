pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

rootProject.name = "Hyperverse"

include(":hyperverse-nms-common")
include(":hyperverse-core")

include(":hyperverse-nms-1-19")
include(":hyperverse-nms-unsupported")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
