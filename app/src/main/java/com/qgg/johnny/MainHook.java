package com.qgg.Johnny;

import de.robv.android.xposed.*;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import android.app.Activity;
import android.view.WindowManager;

public class MainHook implements IXposedHookLoadPackage {
    
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        String pkg = lpparam.packageName;
        ClassLoader cl = lpparam.classLoader;
        
        // ========== 1. 通用去广告 ==========
        String[] adClasses = {
            "com.google.android.gms.ads.AdView",
            "com.google.android.gms.ads.InterstitialAd",
            "com.bytedance.sdk.openadsdk.TTAdSdk",
            "com.qq.e.ads.ADActivity",
            "com.facebook.ads.AdView",
            "com.baidu.mobads.AdView"
        };
        for (String cls : adClasses) {
            hook(cls, "loadAd", cl);
            hook(cls, "show", cl);
            hook(cls, "init", cl);
        }
        
        // ========== 2. 破解VIP ==========
        String[] vipClasses = {
            "com.tencent.qqmusic.business.svip.SVipUtil",
            "com.bilibili.app.comm.benefit.BenefitApi",
            "cn.wps.moffice.common.PremiumService",
            "com.zhihu.android.api.model.Privilege",
            "org.telegram.ui.Components.Premium.PremiumFeature"
        };
        for (String cls : vipClasses) {
            hookTrue(cls, "isVip", cl);
            hookTrue(cls, "isSVip", cl);
            hookTrue(cls, "isPremium", cl);
        }
        
        // ========== 3. 微信 ==========
        if (pkg.equals("com.tencent.mm")) {
            hook("com.tencent.mm.plugin.sns.ad.b", "isAd", cl);
        }
        
        // ========== 4. 抖音 ==========
        if (pkg.equals("com.ss.android.ugc.aweme")) {
            hook("com.ss.android.ugc.aweme.feed.ad.AdFeedSDK", "isAd", cl);
        }
        
        // ========== 5. B站 ==========
        if (pkg.equals("tv.danmaku.bili")) {
            hook("com.bilibili.ad.AdLoader", "loadAd", cl);
            hookTrue("com.bilibili.app.comm.benefit.BenefitApi", "isVip", cl);
        }
        
        // ========== 6. QQ音乐 ==========
        if (pkg.equals("com.tencent.qqmusic")) {
            hookTrue("com.tencent.qqmusic.business.svip.SVipUtil", "isSVip", cl);
            hookTrue("com.tencent.qqmusic.business.vip.VipUtil", "isVip", cl);
        }
        
        // ========== 7. WPS ==========
        if (pkg.equals("cn.wps.moffice_eng")) {
            hookTrue("cn.wps.moffice.common.PremiumService", "isPremium", cl);
        }
        
        // ========== 8. 知乎 ==========
        if (pkg.equals("com.zhihu.android")) {
            hookTrue("com.zhihu.android.api.model.Privilege", "isVip", cl);
            hook("com.zhihu.ad.AdFetcher", "fetch", cl);
        }
        
        // ========== 9. Telegram ==========
        if (pkg.equals("org.telegram.messenger")) {
            hookTrue("org.telegram.ui.Components.Premium.PremiumFeature", "isAvailable", cl);
        }
        
        // ========== 10. 微博 ==========
        if (pkg.equals("com.sina.weibo")) {
            hook("com.sina.weibo.ad.AdManager", "getAd", cl);
        }
        
        // ========== 11. 淘宝 ==========
        if (pkg.equals("com.taobao.taobao")) {
            hook("com.taobao.ad.AdManager", "showSplashAd", cl);
        }
        
        // ========== 12. 百度 ==========
        if (pkg.equals("com.baidu.searchbox")) {
            hook("com.baidu.ad.AdSDK", "showAd", cl);
        }
        
        // ========== 13. 123云盘 ==========
        if (pkg.equals("com.mfcloudcalculate.networkdisk")) {
            hook("com.mfcloudcalculate.networkdisk.ad.AdController", "showAd", cl);
            hookTrue("com.mfcloudcalculate.networkdisk.util.AdUtil", "isVIP", cl);
        }
        
        // ========== 14. 网易云音乐 ==========
        if (pkg.equals("com.netease.cloudmusic")) {
            hookTrue("com.netease.cloudmusic.member.MemberManager", "isVip", cl);
        }
        
        // ========== 15. 小红书 ==========
        if (pkg.equals("com.xingin.xhs")) {
            hookTrue("com.xingin.xhs.member.MemberApi", "isMember", cl);
        }
        
        // ========== 16. 过VPN检测 ==========
        hook("android.net.ConnectivityManager", "getActiveNetwork", cl);
        
        // ========== 17. 过实名认证 ==========
        if (pkg.contains("tmgp") || pkg.contains("king")) {
            hookTrue("com.tencent.tmgp.realname.RealNameChecker", "isNeedRealName", cl);
        }
        
        // ========== 18. 去除弹窗 ==========
        hook("com.cloudinject.cloudinject.CloudInject", "showDialog", cl);
        hook("com.qingyu.popup.QingYuPopup", "show", cl);
        
        // ========== 19. 网易游戏 ==========
        if (pkg.contains("netease")) {
            hookTrue("com.netease.game.GameConfig", "isAndroid", cl);
        }
        
        // ========== 20. 抖音防断网 ==========
        if (pkg.contains("douyin") || pkg.contains("aweme")) {
            hookTrue("com.ss.android.common.app.AppConfig", "useCronet", cl);
        }
        
        // ========== 21. 防截图 ==========
        hookActivityOnCreate(cl);
        
        // ========== 22. JustTrustMe ==========
        try {
            Class<?> trustManager = XposedHelpers.findClass("com.android.org.conscrypt.TrustManagerImpl", cl);
            XposedBridge.hookAllMethods(trustManager, "checkServerTrusted", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) {
                    param.setResult(null);
                }
            });
        } catch (Throwable e) {}
        
        // ========== 23. replaceSu+ ==========
        hookReturn("android.os.Shell", "getSuPath", "/data/adb/ksu/bin/su", cl);
        
        // ========== 24. 代理白名单 ==========
        if (pkg.contains("shadowsocks") || pkg.contains("v2ray")) {
            hookTrue("android.net.ConnectivityManager", "bindProcessToNetwork", cl);
        }
        
        XposedBridge.log("Johnny模块: 已加载 - " + pkg);
    }
    
    private void hook(String className, String methodName, ClassLoader cl) {
        try {
            Class<?> c = XposedHelpers.findClass(className, cl);
            XposedBridge.hookAllMethods(c, methodName, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) {
                    param.setResult(null);
                }
            });
        } catch (Throwable e) {}
    }
    
    private void hookTrue(String className, String methodName, ClassLoader cl) {
        try {
            Class<?> c = XposedHelpers.findClass(className, cl);
            XposedBridge.hookAllMethods(c, methodName, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) {
                    param.setResult(true);
                }
            });
        } catch (Throwable e) {}
    }
    
    private void hookReturn(String className, String methodName, Object value, ClassLoader cl) {
        try {
            Class<?> c = XposedHelpers.findClass(className, cl);
            XposedBridge.hookAllMethods(c, methodName, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) {
                    param.setResult(value);
                }
            });
        } catch (Throwable e) {}
    }
    
    private void hookActivityOnCreate(ClassLoader cl) {
        try {
            XposedBridge.hookAllMethods(Activity.class, "onCreate", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) {
                    Activity a = (Activity) param.thisObject;
                    a.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);
                }
            });
        } catch (Throwable e) {}
    }
}