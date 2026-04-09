package com.qgg.Johnny;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RulesFragment extends Fragment {
    
    private RecyclerView recyclerView;
    private FloatingActionButton fabAdd;
    private RuleAdapter adapter;
    private List<MainHook.UserRule> ruleList = new ArrayList<>();
    private SharedPreferences prefs;
    private Gson gson = new Gson();
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rules, container, false);
    }
    
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        recyclerView = view.findViewById(R.id.recycler_view);
        fabAdd = view.findViewById(R.id.fab_add);
        prefs = getContext().getSharedPreferences("johnny_rules", Context.MODE_PRIVATE);
        
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RuleAdapter();
        recyclerView.setAdapter(adapter);
        
        loadRules();
        
        fabAdd.setOnClickListener(v -> showAddRuleDialog());
    }
    
    private void loadRules() {
        String json = prefs.getString("rules", "");
        if (!json.isEmpty()) {
            Type type = new TypeToken<List<MainHook.UserRule>>(){}.getType();
            ruleList = gson.fromJson(json, type);
        } else {
            ruleList = new ArrayList<>();
            ruleList.add(new MainHook.UserRule("com.tencent.qqmusic", "com.tencent.qqmusic.business.svip.SVipUtil", "isSVip", "true", "解锁QQ音乐超级会员"));
            ruleList.add(new MainHook.UserRule("tv.danmaku.bili", "com.bilibili.app.comm.benefit.BenefitApi", "isVip", "true", "解锁B站大会员"));
            saveRules();
        }
        adapter.notifyDataSetChanged();
    }
    
    private void saveRules() {
        String json = gson.toJson(ruleList);
        prefs.edit().putString("rules", json).apply();
    }
    
    private void showAddRuleDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("添加自定义规则");
        
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_app, null);
        EditText etPackageName = view.findViewById(R.id.et_package_name);
        EditText etAppName = view.findViewById(R.id.et_app_name);
        EditText etVersion = view.findViewById(R.id.et_version);
        EditText etTips = view.findViewById(R.id.et_tips);
        EditText etCompatible = view.findViewById(R.id.et_compatible);
        
        etAppName.setHint("类名");
        etVersion.setHint("方法名");
        etTips.setHint("返回值(true/false/null)");
        etCompatible.setHint("描述");
        
        builder.setView(view);
        builder.setPositiveButton("添加", (dialog, which) -> {
            String packageName = etPackageName.getText().toString().trim();
            String className = etAppName.getText().toString().trim();
            String methodName = etVersion.getText().toString().trim();
            String returnType = etTips.getText().toString().trim();
            String description = etCompatible.getText().toString().trim();
            
            if (!packageName.isEmpty() && !className.isEmpty() && !methodName.isEmpty()) {
                ruleList.add(new MainHook.UserRule(packageName, className, methodName, returnType, description));
                saveRules();
                adapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "添加成功", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", null);
        builder.show();
    }
    
    class RuleAdapter extends RecyclerView.Adapter<RuleAdapter.ViewHolder> {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_app, parent, false);
            return new ViewHolder(view);
        }
        
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            MainHook.UserRule rule = ruleList.get(position);
            holder.tvAppName.setText(rule.packageName);
            holder.tvPackageName.setText(rule.className);
            holder.tvVersion.setText(rule.methodName);
            holder.tvTips.setText("返回值: " + rule.returnType);
            holder.tvCompatible.setText(rule.description);
            holder.switchEnabled.setChecked(rule.enabled);
        }
        
        @Override
        public int getItemCount() {
            return ruleList.size();
        }
        
        class ViewHolder extends RecyclerView.ViewHolder {
            TextView tvAppName, tvPackageName, tvVersion, tvTips, tvCompatible;
            android.widget.SwitchCompat switchEnabled;
            ViewHolder(View itemView) {
                super(itemView);
                tvAppName = itemView.findViewById(R.id.tv_app_name);
                tvPackageName = itemView.findViewById(R.id.tv_package_name);
                tvVersion = itemView.findViewById(R.id.tv_version);
                tvTips = itemView.findViewById(R.id.tv_tips);
                tvCompatible = itemView.findViewById(R.id.tv_compatible);
                switchEnabled = itemView.findViewById(R.id.switch_enabled);
            }
        }
    }
}