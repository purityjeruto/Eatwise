plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id ("kotlin-kapt")
}

android {
    namespace = "com.purity.eatwise"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.purity.eatwise"
        minSdk = 23
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.play.services.analytics.impl)
    implementation(libs.support.annotations)
    implementation(libs.androidx.animation.core.android)
    implementation(libs.androidx.animation.core.android)
    implementation(libs.androidx.animation.core.android)
    implementation(libs.androidx.animation.core.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)



    //Room
    implementation ("androidx.room:room-runtime:2.6.1")
    implementation ("androidx.room:room-runtime-android:2.7.1")
    kapt ("androidx.room:room-compiler:2.6.1")
    implementation ("androidx.room:room-ktx:2.6.1")

    // Image Loading (Coil for Jetpack Compose)
    implementation ("io.coil-kt:coil-compose:2.4.0")

    //live data
    implementation("androidx.compose.runtime:runtime-livedata:1.6.4")
    implementation("com.google.android.gms:play-services-analytics-impl:18.2.0")
    implementation("com.google.android.gms.analytics.ecommerce.org.gradle-core:2.2")
    implementation("androidx.compose.animation:animation-core-android:1.8.1")

}

