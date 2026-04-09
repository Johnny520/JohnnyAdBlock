# 核心：禁用依赖动态变更，彻底解决配置修改报错
android.enableR8.fullMode=true
android.useAndroidX=true
android.enableJetifier=true

# Gradle 优化配置，避免CI环境冲突
org.gradle.jvmargs=-Xmx2048m -Dfile.encoding=UTF-8
org.gradle.warning.mode=none
org.gradle.caching=false
org.gradle.daemon=false
org.gradle.parallel=false
org.gradle.configuration-cache=false