package com.qgg.Johnny;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

public class SettingsFragment extends Fragment {
    
    private SharedPreferences prefs;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }
    
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        prefs = getContext().getSharedPreferences("johnny_config", Context.MODE_PRIVATE);
        
        initSwitch(view, R.id.switch_flag_secure, "flag_secure");
        initSwitch(view, R.id.switch_vpn_detect, "vpn_detect");
        initSwitch(view, R.id.switch_real_name, "real_name");
        initSwitch(view, R.id.switch_popup, "popup");
        initSwitch(view, R.id.switch_ad, "ad");
        initSwitch(view, R.id.switch_vip, "vip");
        initSwitch(view, R.id.switch_netease, "netease");
        initSwitch(view, R.id.switch_replace_su, "replace_su");
        initSwitch(view, R.id.switch_just_trust_me, "just_trust_me");
        initSwitch(view, R.id.switch_douyin_http, "douyin_http");
        initSwitch(view, R.id.switch_proxy_whitelist, "proxy_whitelist");
    }
    
    private void initSwitch(View view, int id, String key) {
        SwitchCompat sw = view.findViewById(id);
        if (sw != null) {
            sw.setChecked(prefs.getBoolean(key, key.equals("ad") || key.equals("vip")));
            sw.setOnCheckedChangeListener((buttonView, isChecked) -> {
                prefs.edit().putBoolean(key, isChecked).apply();
                String[] names = {"防截图", "过VPN", "过实名", "去弹窗", "去广告", "破解VIP", "网易游戏", "replaceSu+", "JustTrustMe", "抖音防断网", "代理白名单"};
                int[] ids = {R.id.switch_flag_secure, R.id.switch_vpn_detect, R.id.switch_real_name, R.id.switch_popup, R.id.switch_ad, R.id.switch_vip, R.id.switch_netease, R.id.switch_replace_su, R.id.switch_just_trust_me, R.id.switch_douyin_http, R.id.switch_proxy_whitelist};
                for (int i = 0; i < ids.length; i++) {
                    if (ids[i] == id) {
                        Toast.makeText(getContext(), names[i] + (isChecked ? "已开启" : "已关闭"), Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
            });
        }
    }
}