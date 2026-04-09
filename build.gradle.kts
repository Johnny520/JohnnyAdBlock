plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.20" apply false
}

// 全局禁用 Kotlin 构建统计服务，彻底解决 buildFlowServiceProperty 报错
allprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            freeCompilerArgs += "-Xno-param-assertions"
        }
    }
    plugins.withId("org.jetbrains.kotlin.android") {
        (this as org.jetbrains.kotlin.gradle.plugin.KotlinAndroidPluginWrapper)
            .extension
            .let { ext ->
                ext.explicitApiMode = org.jetbrains.kotlin.gradle.dsl.ExplicitApiMode.Disabled
                // 核心：禁用构建统计服务
                ext.buildFlowServiceEnabled.set(false)
            }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}