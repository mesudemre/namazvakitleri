plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt)
    id("com.google.gms.google-services")
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()


    defaultConfig {
        applicationId = "com.mesutemre.namazvakitleri"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 7
        versionName = "1.7"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        create("alphaRelease")
        create("betaRelease")

        signingConfigs {
            getByName("debug") {
                keyAlias = "namazvakitleri"
                keyPassword = "Msd19916"
                storeFile = file("/Users/mesutemrecelenk/Documents/mesutandroid/namazvakitleri.jks")
                storePassword = "Msd19916"
            }
            create("release") {
                keyAlias = "namazvakitleri"
                keyPassword = "Msd19916"
                storeFile = file("/Users/mesutemrecelenk/Documents/mesutandroid/namazvakitleri.jks")
                storePassword = "Msd19916"
            }
        }

        release {
            getByName("release") {
                isMinifyEnabled = true
                isDebuggable = false
                signingConfig = signingConfigs.getByName("debug")
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
                buildConfigField("String", "baseApiUrl", "\"https://ezanvakti.herokuapp.com/\"")
                buildConfigField("String", "tarihteBugunApiUrl", "\"https://api.ubilisim.com/\"")
            }
            getByName("alphaRelease") {
                isMinifyEnabled = true
                isDebuggable = true
                signingConfig = signingConfigs.getByName("debug")
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
                buildConfigField("String", "baseApiUrl", "\"https://ezanvakti.herokuapp.com/\"")
                buildConfigField("String", "tarihteBugunApiUrl", "\"https://api.ubilisim.com/\"")
            }
            getByName("betaRelease") {
                isMinifyEnabled = false
                isDebuggable = false
                signingConfig = signingConfigs.getByName("release")
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
                buildConfigField("String", "baseApiUrl", "\"https://ezanvakti.herokuapp.com/\"")
                buildConfigField("String", "tarihteBugunApiUrl", "\"https://api.ubilisim.com/\"")
            }
        }
        debug {
            isMinifyEnabled = false
            isDebuggable = true
            buildConfigField("String", "baseApiUrl", "\"https://ezanvakti.herokuapp.com/\"")
            buildConfigField("String", "tarihteBugunApiUrl", "\"https://api.ubilisim.com/\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packagingOptions {
        // Multiple dependency bring these files in. Exclude them to enable
        // our test APK to build (has no effect on our AARs)
        excludes += "/META-INF/AL2.0"
        excludes += "/META-INF/LGPL2.1"
    }
}

dependencies {

    val composeBom = platform(libs.androidx.compose.bom)
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlin.reflection)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.constraintlayout.compose)
    implementation(libs.androidx.core.splashscreen)

    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.ui.util)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.animation)
    implementation(libs.androidx.compose.material.iconsExtended)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.intuit)
    implementation(libs.datastore)
    implementation(libs.hilt.android)
    implementation(libs.hilt.common)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.lottie)
    implementation(libs.okhttp3)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.base)
    implementation(libs.retrofit.rxjava)
    implementation(libs.retrofit.converter.scalars)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.gson)
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    kapt(libs.androidx.hilt.compiler)
    kapt(libs.hilt.common)
    kapt(libs.hilt.ext.compiler)
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.androidx.work.hilt)
    implementation(libs.google.services.firebase.database)
    implementation(libs.google.services.firebase.messaging)
    implementation(libs.androidx.constraintlayout.legacy)
    implementation(libs.coil)
    implementation(libs.picasso)

    debugImplementation(libs.androidx.compose.ui.tooling)


    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.androidx.test.ext.junit)
}