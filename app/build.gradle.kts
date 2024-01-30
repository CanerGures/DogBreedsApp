plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.devtools.ksp")
    id("kotlin-kapt")
}

android {
    namespace = "com.canergures.dogbreedsapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.canergures.dogbreedsapp"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.5"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.android.material)
    implementation(libs.android.material.icons)
    implementation(libs.android.material.google)
    implementation(libs.androidx.legacy)
    implementation(libs.androidx.work)

    // Navigation Component
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)

    // ViewModel
    implementation(libs.androidx.lifecycle.viewmodel)

    // LiveData
    implementation(libs.androidx.lifecycle.livedata)

    // Hilt
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation)
    kapt(libs.hilt.compiler)
    kapt(libs.androidx.hilt.compiler)
    implementation(libs.androidx.hilt.work)

    //Navigation Animation
    implementation(libs.navAnimation)

    //ViewPager
    implementation(libs.viewpager)

    //Coil
    implementation(libs.coil.kt)
    implementation(libs.coil.svg)
    implementation(libs.coil.compose)

    //Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.ui.viewbinding)
    //debugImplementation(libs.androidx.ui.tooling)

    // JUnit
    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.test.ext)

    // Espresso
    androidTestImplementation(libs.androidx.test.espresso)

    //Mockk
    testImplementation(libs.mockk)

    //Coroutines test

    testImplementation(libs.coroutinesTest)

}