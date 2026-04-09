package com.qgg.Johnny;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AppsFragment extends Fragment {
    
    private RecyclerView recyclerView;
    private FloatingActionButton fabAdd;
    private AppAdapter adapter;
    private List<AppItem> appList = new ArrayList<>();
    private SharedPreferences prefs;
    private Gson gson = new Gson();
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_apps, container, false);
    }
    
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        recyclerView = view.findViewById(R.id.recycler_view);
        fabAdd = view.findViewById(R.id.fab_add);
        prefs = getContext().getSharedPreferences("johnny_apps", Context.MODE_PRIVATE);
        
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AppAdapter();
        recyclerView.setAdapter(adapter);
        
        loadApps();
        
        fabAdd.setOnClickListener(v -> showAddAppDialog());
    }
    
    private void loadApps() {
        String json = prefs.getString("apps", "");
        if (!json.isEmpty()) {
            Type type = new TypeToken<List<AppItem>>(){}.getType();
            appList = gson.fromJson(json, type);
        } else {
            appList = new ArrayList<>();
            appList.add(new AppItem("com.tencent.qqmusic", "QQ音乐", "20.3.0.8", true, "解锁超级会员音效", "全版本通杀"));
            appList.add(new AppItem("tv.danmaku.bili", "哔哩哔哩", "8.89.0", true, "解锁大会员", "全版本通杀"));
            appList.add(new AppItem("cn.wps.moffice_eng", "WPS Office", "14.42.0", true, "解锁会员", "全版本通杀"));
            appList.add(new AppItem("org.telegram.messenger", "Telegram", "12.6.4", true, "解锁本地大会员", "全版本通杀"));
            appList.add(new AppItem("com.zhihu.android", "知乎", "8.0.0", true, "解锁会员", "全版本通杀"));
            saveApps();
        }
        adapter.notifyDataSetChanged();
    }
    
    private void saveApps() {
        String json = gson.toJson(appList);
        prefs.edit().putString("apps", json).apply();
    }
    
    private void showAddAppDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("添加应用");
        
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_app, null);
        EditText etPackageName = view.findViewById(R.id.et_package_name);
        EditText etAppName = view.findViewById(R.id.et_app_name);
        EditText etVersion = view.findViewById(R.id.et_version);
        EditText etTips = view.findViewById(R.id.et_tips);
        EditText etCompatible = view.findViewById(R.id.et_compatible);
        
        builder.setView(view);
        builder.setPositiveButton("添加", (dialog, which) -> {
            String packageName = etPackageName.getText().toString().trim();
            String appName = etAppName.getText().toString().trim();
            String version = etVersion.getText().toString().trim();
            String tips = etTips.getText().toString().trim();
            String compatible = etCompatible.getText().toString().trim();
            
            if (!packageName.isEmpty() && !appName.isEmpty()) {
                appList.add(new AppItem(packageName, appName, version, true, tips, compatible));
                saveApps();
                adapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "添加成功", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", null);
        builder.show();
    }
    
    private void showMenuDialog(int position) {
        AppItem item = appList.get(position);
        String[] items = {"编辑", "保存到本地", "分享"};
        
        new AlertDialog.Builder(getContext())
            .setTitle(item.appName)
            .setItems(items, (dialog, which) -> {
                if (which == 0) showEditDialog(position);
                else if (which == 1) saveToLocal(position);
                else if (which == 2) shareConfig(position);
            })
            .show();
    }
    
    private void showEditDialog(int position) {
        AppItem item = appList.get(position);
        
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("编辑应用");
        
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_app, null);
        EditText etPackageName = view.findViewById(R.id.et_package_name);
        EditText etAppName = view.findViewById(R.id.et_app_name);
        EditText etVersion = view.findViewById(R.id.et_version);
        EditText etTips = view.findViewById(R.id.et_tips);
        EditText etCompatible = view.findViewById(R.id.et_compatible);
        
        etPackageName.setText(item.packageName);
        etAppName.setText(item.appName);
        etVersion.setText(item.version);
        etTips.setText(item.tips);
        etCompatible.setText(item.compatibleNote);
        
        builder.setView(view);
        builder.setPositiveButton("保存", (dialog, which) -> {
            item.packageName = etPackageName.getText().toString().trim();
            item.appName = etAppName.getText().toString().trim();
            item.version = etVersion.getText().toString().trim();
            item.tips = etTips.getText().toString().trim();
            item.compatibleNote = etCompatible.getText().toString().trim();
            saveApps();
            adapter.notifyItemChanged(position);
            Toast.makeText(getContext(), "保存成功", Toast.LENGTH_SHORT).show();
        });
        builder.setNegativeButton("取消", null);
        builder.show();
    }
    
    private void saveToLocal(int position) {
        AppItem item = appList.get(position);
        String json = gson.toJson(item);
        try {
            String filename = item.packageName + ".json";
            FileOutputStream fos = getContext().openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write(json.getBytes());
            fos.close();
            Toast.makeText(getContext(), "已保存到: " + filename, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getContext(), "保存失败", Toast.LENGTH_SHORT).show();
        }
    }
    
    private void shareConfig(int position) {
        AppItem item = appList.get(position);
        String json = gson.toJson(item);
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, json);
        startActivity(Intent.createChooser(shareIntent, "分享配置"));
    }
    
    class AppAdapter extends RecyclerView.Adapter<AppAdapter.ViewHolder> {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_app, parent, false);
            return new ViewHolder(view);
        }
        
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            AppItem item = appList.get(position);
            holder.tvAppName.setText(item.appName);
            holder.tvPackageName.setText(item.packageName);
            holder.tvVersion.setText(item.version);
            holder.tvTips.setText(item.tips);
            holder.tvCompatible.setText(item.compatibleNote);
            holder.switchEnabled.setChecked(item.isEnabled);
            holder.btnMenu.setOnClickListener(v -> showMenuDialog(position));
        }
        
        @Override
        public int getItemCount() {
            return appList.size();
        }
        
        class ViewHolder extends RecyclerView.ViewHolder {
            TextView tvAppName, tvPackageName, tvVersion, tvTips, tvCompatible;
            SwitchCompat switchEnabled;
            ImageView btnMenu;
            ViewHolder(View itemView) {
                super(itemView);
                tvAppName = itemView.findViewById(R.id.tv_app_name);
                tvPackageName = itemView.findViewById(R.id.tv_package_name);
                tvVersion = itemView.findViewById(R.id.tv_version);
                tvTips = itemView.findViewById(R.id.tv_tips);
                tvCompatible = itemView.findViewById(R.id.tv_compatible);
                switchEnabled = itemView.findViewById(R.id.switch_enabled);
                btnMenu = itemView.findViewById(R.id.btn_menu);
            }
        }
    }
}