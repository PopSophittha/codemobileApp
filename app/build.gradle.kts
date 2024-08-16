plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id ("kotlin-android")
    id ("kotlin-kapt")
}

android {
    namespace = "com.example.codemobilechallengeapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.codemobilechallengeapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    android.buildFeatures.dataBinding = true
    android.buildFeatures.viewBinding = true

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation ("androidx.core:core-ktx:1.8.0")
    implementation ("androidx.appcompat:appcompat:1.5.0")
    implementation ("com.google.android.material:material:1.12.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")

    /* PGW SDK dependencies */
    implementation("com.google.code.gson:gson:2.9.0")

    /* coroutine */
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3")

    /* lifecycle */
    implementation ("androidx.lifecycle:lifecycle-runtime:2.5.1")
    implementation ("androidx.lifecycle:lifecycle-common-java8:2.5.1")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")

    /* Retrofit */
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

    /* koin */
    implementation("io.insert-koin:koin-android:3.1.2")

    /* data binding */
    implementation ("androidx.databinding:databinding-runtime:8.1.4")

    /* even bus */
    implementation("org.greenrobot:eventbus:3.3.1")

    /* glide */
    implementation("com.github.bumptech.glide:glide:4.12.0")


}