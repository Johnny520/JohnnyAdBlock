  # JohnnyAdBlock 🚫
一款轻量、纯净的Android广告拦截工具，纯Java开发，无Kotlin依赖，开箱即用。

---

## ✨ 项目特性
- **纯Java实现**：彻底移除Kotlin相关依赖，编译更稳定，适配所有Android开发环境
- **轻量无冗余**：核心功能简洁高效，安装包体积小，运行流畅
- **自动打包构建**：集成GitHub Actions，提交代码自动编译APK，打包完成直接下载
- **标准Android项目结构**：遵循Android开发规范，易于二次开发和维护

---

## 📁 项目结构
 
 ```text
JohnnyAdBlock/
├─ .github/
│  └─ workflows/
│     └─ android.yml          # GitHub Actions 自动打包配置
├─ app/
│  ├─ src/
│  │  └─ main/
│  │     ├─ java/
│  │     │  └─ com/qgg/Johnny/
│  │     │     └─ MainActivity.java  # 主Activity（纯Java）
│  │     ├─ res/
│  │     │  ├─ layout/
│  │     │  │  └─ activity_main.xml   # 主界面布局
│  │     │  ├─ values/
│  │     │  │  ├─ strings.xml         # 字符串资源
│  │     │  │  └─ themes.xml          # 主题配置
│  │     └─ AndroidManifest.xml      # 应用清单文件
│  └─ build.gradle.kts                # App模块Gradle配置（纯Java）
├─ build.gradle.kts                   # 根项目Gradle配置
├─ gradle.properties                  # 全局Gradle配置
└─ settings.gradle.kts                # 项目仓库配置
 
plaintext  

---

## 🚀 快速开始
### 1. 环境要求
- Android Studio Hedgehog | 2023.1.1 及以上
- JDK 17
- Gradle 8.2 及以上

### 2. 本地编译
1.  克隆项目到本地
    ```bash
    git clone https://github.com/Johnny520/JohnnyAdBlock.git
    ```
2.  用Android Studio打开项目，等待Gradle同步完成
3.  点击「Run」按钮，直接编译运行APK

### 3. 在线自动打包（推荐）
- 提交代码到`main`分支，GitHub Actions自动触发打包
- 进入仓库「Actions」标签，查看打包进度
- 打包完成后，在「Artifacts」中直接下载APK安装包

---

## 📦 下载安装
- 最新Release版本：[点击下载](sslocal://flow/file_open?url=https%3A%2F%2Fgithub.com%2FJohnny520%2FJohnnyAdBlock%2Freleases&flow_extra=eyJsaW5rX3R5cGUiOiJjb2RlX2ludGVycHJldGVyIn0=)
- 每次提交自动生成最新APK，永久保存，随时下载

---

## 🛠️ 技术栈
- **开发语言**：Java 17
- **构建工具**：Gradle 8.2
- **Android版本**：compileSdk 34，minSdk 29，targetSdk 34
- **依赖库**：AndroidX、Material Design、ConstraintLayout
- **CI/CD**：GitHub Actions

---

## 📄 开源协议
本项目基于 [MIT License](sslocal://flow/file_open?url=LICENSE&flow_extra=eyJsaW5rX3R5cGUiOiJjb2RlX2ludGVycHJldGVyIn0=) 开源，可自由使用、修改和分发。

---

## 🙋‍♂️ 作者
Johnny520
- GitHub：[https://github.com/Johnny520](sslocal://flow/file_open?url=https%3A%2F%2Fgithub.com%2FJohnny520&flow_extra=eyJsaW5rX3R5cGUiOiJjb2RlX2ludGVycHJldGVyIn0=)
 
 
 
 
