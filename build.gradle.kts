import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    // 这里保留你原本有的插件，通常是下面这两个
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
}

// --- 修复开始：替换掉报错的清理和配置代码 ---
tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}
// --- 修复结束 ---

// 如果下面还有 allprojects 或 subprojects 块，请保持原样不动
// 如果没有，保持上面的代码即可
