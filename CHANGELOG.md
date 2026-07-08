# JohnnyAdBlock 更新日志

## v1.0.0
- 修复 release 构建签名问题：补全 signingConfig（使用 AGP 内置 debug keystore），GitHub Actions 现在可正常打包出可安装的 APK。
- 修正 xposed_init 中的包名大小写（com.qgg.Johnny -> com.qgg.johnny），确保 LSPosed 能正确加载 Hook。
- 补全 LSPosed 模块元数据（xposedmodule / xposedminversion / xposeddescription），让模块能被 LSPosed Manager 正确识别。
- 优化 CI：推送 v* tag 时自动生成 GitHub Release 并附带本更新说明。
