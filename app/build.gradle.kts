plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.yuruneji.myapplication"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.yuruneji.myapplication"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "API_URL_BASE", "\"${project.properties["api.url.base"]}\"")
        buildConfigField("String", "API_URL_DEVELOP", "\"${project.properties["api.url.develop"]}\"")
        buildConfigField("String", "API_URL_STAGING", "\"${project.properties["api.url.staging"]}\"")
        buildConfigField("String", "API_URL_PRODUCTION", "\"${project.properties["api.url.production"]}\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
        dataBinding = true
    }
    packagingOptions {
        resources.excludes.add("META-INF/*")
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.fragment.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // DI: Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    // DB: Room
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    // Camera: CameraX
    implementation(libs.androidx.camera.core)
    implementation(libs.androidx.camera.camera2)
    implementation(libs.androidx.camera.lifecycle)
    implementation(libs.androidx.camera.video)
    implementation(libs.androidx.camera.view)
    implementation(libs.androidx.camera.mlkit.vision)
    implementation(libs.androidx.camera.extensions)

    // mobile SDK: ML Kit
    implementation(libs.barcode.scanning)
    implementation(libs.face.detection)

    // HTTP Client:Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.moshi)

    // Json: Moshi
    implementation(libs.moshi.kotlin)

    // Log: Timber
    implementation(libs.timber)

    // Crypto: Security-Crypto
    implementation(libs.androidx.security.crypto)

    // DataStore
    implementation(libs.androidx.datastore.preferences)

    // WorkManager
    implementation(libs.androidx.work.runtime.ktx)

    // Apache Commons Lang 3.12.0
    implementation(libs.commons.lang3)
    // Apache Commons Codec 1.15
    implementation(libs.commons.codec)
    // Apache Commons Net 3.11.1
    implementation(libs.commons.net)

    // Location
    implementation(libs.play.services.location)

    // Debug: LeakCanary
    debugImplementation(libs.leakcanary.android)

    // Robolectric environment
    testImplementation(libs.androidx.test.core)
    // Mockito framework
    testImplementation(libs.mockito.core)
    // Mockito-kotlin
    testImplementation(libs.mockito.kotlin)
    // Mockk framework
    testImplementation(libs.mockk)

    // Module
    implementation(project(":module:mylibrary"))

    // ktor
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.call.logging)
    implementation(libs.ktor.server.auth)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.slf4j.android)

}
