# JohnnyAdBlock 🚫

> **作者**：文强哥（Johnny520）· GitHub：<https://github.com/Johnny520> · 许可证：MIT

一款基于 **LSPosed** 的轻量去广告模块，纯 Java 开发（无 Kotlin 依赖），开箱即用。

---

## ✨ 特性

- **LSPosed 模块**：在 LSPosed Manager 中勾选作用域即可对目标 App 生效。
- **纯 Java 实现**：无 Kotlin 依赖，编译稳定，适配所有 Android 开发环境。
- **轻量无冗余**：核心功能简洁高效，安装包体积小，运行流畅。
- **自动构建发布**：集成 GitHub Actions，推送 `v*` tag 自动构建**正式签名版** APK 并发布 Release。
- **标准工程结构**：遵循 Android 开发规范，易于二次开发和维护。

---

## 📢 声明

本项目仅用于**学习交流与技术研究**，严禁用于商业用途、非法用途及损害他人权益的行为。使用本工具所产生的一切后果，由使用者自行承担。

---

## 🚀 使用

1. 手机已安装 **LSPosed**（基于 Magisk / KernelSU 的 Zygisk）。
2. 从 [Releases](https://github.com/Johnny520/JohnnyAdBlock/releases) 下载 `app-release.apk` 并安装。
3. 打开 **LSPosed Manager → 模块**，勾选 **JohnnyAdBlock**，在「作用域」里选中要拦截广告的 App。
4. 重启目标 App 生效。

---

## 🛠️ 技术栈

- **语言**：Java 17
- **构建**：Gradle 8.2 + AGP 8.2.0
- **Android**：compileSdk 34 / minSdk 29 / targetSdk 34
- **依赖**：AndroidX、Material Design、ConstraintLayout、Gson
- **Xposed API**：`de.robv.android.xposed:api:82`（compileOnly）
- **CI/CD**：GitHub Actions（构建 + 正式签名 + 发布）

---

## 📁 项目结构

```
JohnnyAdBlock/
├─ .github/workflows/android.yml   # 自动构建 + 签名 + 发布
├─ app/
│  ├─ src/main/
│  │  ├─ java/com/qgg/johnny/
│  │  │  ├─ MainHook.java          # LSPosed 入口与广告拦截逻辑
│  │  │  ├─ MainActivity.java      # 主界面
│  │  │  ├─ HomeFragment.java      # 总开关
│  │  │  ├─ AppsFragment.java      # 应用列表
│  │  │  ├─ RulesFragment.java     # 规则管理
│  │  │  └─ SettingsFragment.java  # 设置
│  │  ├─ res/                      # 布局 / 字符串 / 主题 / 图标
│  │  └─ AndroidManifest.xml       # xposedmodule 元数据
│  └─ build.gradle.kts
├─ build.gradle.kts
├─ settings.gradle.kts
├─ gradle.properties
└─ CHANGELOG.md
```

---

## 📦 本地编译

```bash
git clone https://github.com/Johnny520/JohnnyAdBlock.git
cd JohnnyAdBlock
./gradlew assembleRelease
```

> 本地未配置签名密钥时，release 会回退到 debug 签名以便调试；CI 中使用仓库 **Secrets** 里的正式密钥签名。

---

## 📄 开源协议

基于 [MIT License](LICENSE) 开源，可自由使用、修改和分发。
