package com.qgg.Johnny;

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