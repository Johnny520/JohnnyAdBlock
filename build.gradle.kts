plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.24" apply false
}

// 全局禁用 Kotlin 构建统计服务，根治 buildFlowServiceProperty 报错
allprojects {
    plugins.withId("org.jetbrains.kotlin.android") {
        val kotlinExtension = extensions.getByType(org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension::class.java)
        // 核心：彻底关闭构建统计服务
        kotlinExtension.buildFlowServiceEnabled.set(false)
        kotlinExtension.explicitApiMode.set(org.jetbrains.kotlin.gradle.dsl.ExplicitApiMode.Disabled)
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "17"
            freeCompilerArgs += listOf("-Xno-param-assertions", "-Xno-optimized-jvm-ir")
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}