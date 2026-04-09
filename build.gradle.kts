import org.gradle.api.tasks.Delete

plugins {
    // 保持你原有的插件配置，这里根据你的项目推测
    id("com.android.application") version "8.2.0" apply false
    id("com.android.library") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
}

// 修复报错：Unresolved reference 和 Deprecated buildDir
tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}