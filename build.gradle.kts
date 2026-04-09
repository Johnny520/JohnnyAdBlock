plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.24" apply false
}

// 全局禁用 Kotlin 构建统计服务（兼容所有 Kotlin 版本的正确写法）
allprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "17"
            freeCompilerArgs += listOf("-Xno-param-assertions", "-Xno-optimized-jvm-ir")
        }
    }
}

// 修复弃用问题：用 layout.buildDirectory 替代 rootProject.buildDir
tasks.register<Delete>("clean") {
    delete(layout.buildDirectory)
}