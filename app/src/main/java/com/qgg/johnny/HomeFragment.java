package com.qgg.Johnny;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    
    private SwitchCompat globalSwitch;
    private TextView switchStatus, activationStatus, moduleVersion;
    private TextView authMode, apiVersion, xposedApi;
    private TextView systemVersion, deviceModel, systemArch, kernelVersion;
    private TextView totalBlocked, todayBlocked;
    private View statusIndicator;
    private android.content.SharedPreferences prefs;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
    
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        prefs = getContext().getSharedPreferences("johnny_config", android.content.Context.MODE_PRIVATE);
        
        globalSwitch = view.findViewById(R.id.global_switch);
        switchStatus = view.findViewById(R.id.switch_status);
        statusIndicator = view.findViewById(R.id.status_indicator);
        activationStatus = view.findViewById(R.id.activation_status);
        moduleVersion = view.findViewById(R.id.module_version);
        authMode = view.findViewById(R.id.auth_mode);
        apiVersion = view.findViewById(R.id.api_version);
        xposedApi = view.findViewById(R.id.xposed_api);
        systemVersion = view.findViewById(R.id.system_version);
        deviceModel = view.findViewById(R.id.device_model);
        systemArch = view.findViewById(R.id.system_arch);
        kernelVersion = view.findViewById(R.id.kernel_version);
        totalBlocked = view.findViewById(R.id.total_blocked);
        todayBlocked = view.findViewById(R.id.today_blocked);
        
        try {
            String version = getContext().getPackageManager()
                .getPackageInfo(getContext().getPackageName(), 0).versionName;
            moduleVersion.setText(version);
        } catch (Exception e) {
            moduleVersion.setText("1.0.0");
        }
        
        setupGlobalSwitch();
        loadSystemInfo();
        updateActivationStatus();
    }
    
    private void setupGlobalSwitch() {
        boolean isEnabled = prefs.getBoolean("global_switch", true);
        globalSwitch.setChecked(isEnabled);
        MainHook.isGlobalEnabled = isEnabled;
        switchStatus.setText(isEnabled ? "已开启" : "已关闭");
        
        globalSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            prefs.edit().putBoolean("global_switch", isChecked).apply();
            MainHook.isGlobalEnabled = isChecked;
            switchStatus.setText(isChecked ? "已开启" : "已关闭");
            Toast.makeText(getContext(), isChecked ? "全局拦截已开启" : "全局拦截已关闭", Toast.LENGTH_SHORT).show();
        });
    }
    
    private void loadSystemInfo() {
        systemVersion.setText(Build.VERSION.RELEASE + " (API " + Build.VERSION.SDK_INT + ")");
        deviceModel.setText(Build.MANUFACTURER + " " + Build.MODEL);
        systemArch.setText(Build.SUPPORTED_ABIS[0]);
        kernelVersion.setText(getKernelVersion());
        authMode.setText("LSPosed/Root");
        apiVersion.setText(String.valueOf(Build.VERSION.SDK_INT));
        xposedApi.setText("已启用");
    }
    
    private String getKernelVersion() {
        try {
            java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader("/proc/version"));
            String line = reader.readLine();
            reader.close();
            return line != null ? line.substring(0, Math.min(60, line.length())) : "unknown";
        } catch (Exception e) {
            return "unknown";
        }
    }
    
    private void updateActivationStatus() {
        statusIndicator.setBackgroundResource(R.drawable.status_indicator_green);
        activationStatus.setText("已激活");
        activationStatus.setTextColor(ContextCompat.getColor(getContext(), R.color.success_green));
    }
}