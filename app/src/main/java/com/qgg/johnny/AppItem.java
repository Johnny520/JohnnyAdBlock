/*
 * JohnnyAdBlock - 基于 LSPosed 的去广告模块
 * 作者：文强哥 (Johnny520)
 * GitHub: https://github.com/Johnny520
 * License: MIT
 */

package com.qgg.johnny;

public class AppItem {
    public String packageName;
    public String appName;
    public String version;
    public boolean isEnabled;
    public String tips;
    public String compatibleNote;
    
    public AppItem(String packageName, String appName, String version, boolean isEnabled, String tips, String compatibleNote) {
        this.packageName = packageName;
        this.appName = appName;
        this.version = version;
        this.isEnabled = isEnabled;
        this.tips = tips;
        this.compatibleNote = compatibleNote;
    }
}