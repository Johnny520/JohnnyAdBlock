# JohnnyAdBlock 更新日志

## v1.1.0
- 改用仓库 **Secrets** 中的正式签名密钥构建 release APK（不再使用 debug keystore 临时签名）。
- 升级版本：versionCode 2 / versionName 1.1.0。
- 补全全部 Java 源码版权头，作者署名统一为「文强哥（Johnny520）」。
- 重写 README：修正包名路径、清理异常链接、补充 LSPosed 使用说明与签名说明。
- GitHub Actions 推送 `v*` tag 自动产出**正式签名版** APK 并发布 Release。

## v1.0.0
- 修复 release 构建签名问题：补全 signingConfig，GitHub Actions 正常打包可安装 APK。
- 修正 xposed_init 包名大小写（com.qgg.Johnny → com.qgg.johnny），确保 LSPosed 正确加载。
- 补全 LSPosed 模块元数据（xposedmodule / xposedminversion / xposeddescription）。
- 推送 v* tag 自动生成 GitHub Release 并附带更新说明。
