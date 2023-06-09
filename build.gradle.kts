buildscript {
    dependencies {
        classpath(libs.google.services.gms)
    }
}
plugins {
    alias(libs.plugins.gradle.versions)
    alias(libs.plugins.version.catalog.update)
}