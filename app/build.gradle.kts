plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.johnnyadblock"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.johnnyadblock"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
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
}

dependencies {
    // 基础Android依赖
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Gson依赖（解决com.google.gson找不到）
    implementation("com.google.code.gson:gson:2.10.1")

    // Xposed依赖（解决de.robv.android.xposed找不到）
    compileOnly("de.robv.android.xposed:api:82") {
        isTransitive = false
    }
}