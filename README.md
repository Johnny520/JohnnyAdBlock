# JohnnyAdBlock 🚫
一款专为 Android 平台打造的轻量去广告模块，一键屏蔽各类应用广告，还你纯净无干扰的使用体验。

## ✨ 核心功能
- 🛡️ **全场景广告拦截**：支持屏蔽开屏广告、弹窗广告、信息流广告、插屏广告、横幅广告
- ⚡ **轻量无侵入**：不修改应用源码，不影响应用正常功能，运行流畅不卡顿
- 🎨 **自定义规则**：支持手动添加/修改广告拦截规则，灵活适配各类应用
- 🔄 **自动更新规则**：内置规则库，定期更新，适配最新应用广告
- 📱 **全安卓版本适配**：支持 Android 10+ 全版本，兼容主流机型

## 📦 项目结构
JohnnyAdBlock/
├── app/                      # 应用主模块
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/         # 核心业务代码
│   │   │   ├── res/          # 资源文件（图标、布局、样式等）
│   │   │   └── AndroidManifest.xml # 应用清单文件
│   └── build.gradle.kts     # 模块构建配置
├── gradle/                   # Gradle 构建工具
├── build.gradle.kts         # 项目根构建配置
├── gradle.properties         # Gradle 全局配置
├── settings.gradle.kts      # 项目依赖配置
├── gradlew / gradlew.bat     # Gradle 脚本（Linux/Windows）
├── README.md                 # 项目说明文档
├── LICENSE                   # 开源协议
└── .gitignore                # Git 忽略文件配置


## 🛠️ 环境要求
- **开发环境**：Android Studio Hedgehog | 2023.1.1 及以上
- **JDK 版本**：JDK 17 及以上
- **Gradle 版本**：Gradle 8.0 及以上
- **编译 SDK**：Android 14 (API 34) 及以上
- **最低兼容**：Android 10 (API 29)

## 📝 快速开始
### 1. 克隆项目
```bash
git clone https://github.com/Johnny520/JohnnyAdBlock.git
cd JohnnyAdBlock

2. 编译运行
 
1. 用 Android Studio 打开项目，等待依赖同步完成
​
2. 连接 Android 设备（开启 USB 调试）
​
3. 点击「Run」按钮，自动编译并安装到设备
​
4. 打开应用，开启去广告服务，即可生效
 
3. 自定义规则
 
1. 进入应用「规则管理」页面
​
2. 点击「添加规则」，输入广告域名/特征
​
3. 保存后立即生效，无需重启应用
 
📌 更新日志
 
v1.0.0 (2026-04-09)
 
- 首次提交项目基础框架
​
- 完成核心去广告拦截模块开发
​
- 适配 Android 10+ 全版本
​
- 支持自定义广告规则
​
- 完成项目基础文档与开源配置
 
🤝 贡献指南
 
欢迎提交 Issue 反馈问题、提交 PR 优化功能！
 
1. Fork 本仓库
​
2. 创建特性分支 (git checkout -b feature/AmazingFeature)
​
3. 提交修改 (git commit -m 'Add some AmazingFeature')
​
4. 推送到分支 (git push origin feature/AmazingFeature)
​
5. 打开 Pull Request
 
📄 开源协议
 
本项目基于 MIT License 开源，详细协议见 LICENSE 文件。
 
👨‍💻 作者
 
Johnny520
 
- GitHub: https://github.com/Johnny520
​
- 项目地址: https://github.com/Johnny520/JohnnyAdBlock
 
⭐ 鸣谢
 
感谢所有为开源社区做出贡献的开发者！
如果这个项目对你有帮助，欢迎 Star ⭐ 支持一下！