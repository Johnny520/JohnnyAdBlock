package com.qgg.Johnny;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    
    private ViewPager2 viewPager;
    private BottomNavigationView bottomNav;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Johnny模块");
        
        viewPager = findViewById(R.id.view_pager);
        bottomNav = findViewById(R.id.bottom_nav);
        
        setupViewPager();
        setupBottomNav();
    }
    
    private void setupViewPager() {
        MainPagerAdapter adapter = new MainPagerAdapter(this);
        viewPager.setAdapter(adapter);
        
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0: bottomNav.setSelectedItemId(R.id.nav_home); break;
                    case 1: bottomNav.setSelectedItemId(R.id.nav_apps); break;
                    case 2: bottomNav.setSelectedItemId(R.id.nav_rules); break;
                    case 3: bottomNav.setSelectedItemId(R.id.nav_settings); break;
                }
            }
        });
    }
    
    private void setupBottomNav() {
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                viewPager.setCurrentItem(0);
                return true;
            } else if (id == R.id.nav_apps) {
                viewPager.setCurrentItem(1);
                return true;
            } else if (id == R.id.nav_rules) {
                viewPager.setCurrentItem(2);
                return true;
            } else if (id == R.id.nav_settings) {
                viewPager.setCurrentItem(3);
                return true;
            }
            return false;
        });
    }
    
    class MainPagerAdapter extends androidx.viewpager2.adapter.FragmentStateAdapter {
        public MainPagerAdapter(MainActivity activity) {
            super(activity);
        }
        
        @Override
        public int getItemCount() {
            return 4;
        }
        
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0: return new HomeFragment();
                case 1: return new AppsFragment();
                case 2: return new RulesFragment();
                case 3: return new SettingsFragment();
                default: return new HomeFragment();
            }
        }
    }
}