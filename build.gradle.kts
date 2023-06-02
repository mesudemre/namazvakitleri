buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.3.15")
    }
}
plugins {
    alias(libs.plugins.gradle.versions)
    alias(libs.plugins.version.catalog.update)
}