import java.util.Base64

plugins {
    id("com.android.application")
}

android {
    namespace = "com.qgg.johnny"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.qgg.johnny"
        minSdk = 29
        targetSdk = 34
        versionCode = 2
        versionName = "1.1.0"
    }

    signingConfigs {
        create("releaseSign") {
            val storeBase64 = System.getenv("SIGNING_KEY")
            if (storeBase64 != null && storeBase64.isNotBlank()) {
                val keystoreFile = File.createTempFile("release-key", ".jks")
                keystoreFile.deleteOnExit()
                keystoreFile.writeBytes(Base64.getDecoder().decode(storeBase64))
                storeFile = keystoreFile
                storePassword = System.getenv("KEY_STORE_PASSWORD")
                keyAlias = System.getenv("KEY_ALIAS")
                keyPassword = System.getenv("KEY_PASSWORD")
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            // 优先使用 CI 注入的正式签名密钥（Secrets）；本地无密钥时回退到 debug 以便调试
            val hasSigning = !System.getenv("SIGNING_KEY").isNullOrBlank()
            signingConfig = if (hasSigning) signingConfigs.getByName("releaseSign") else signingConfigs.getByName("debug")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.code.gson:gson:2.10.1")
    compileOnly("de.robv.android.xposed:api:82")
}
