package com.example.viralyapplication.ui.activity;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.viralyapplication.R;
import com.example.viralyapplication.ui.fragment.NewFeedFragment;
import com.example.viralyapplication.ui.fragment.NotifyFragment;
import com.example.viralyapplication.ui.fragment.ProfileFragment;
import com.example.viralyapplication.ui.fragment.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainToolbarActivity extends BaseFragmentActivity {

    private static MainToolbarActivity sInstance = null;
    protected Context mContext;
    private TextView tvMainTitle;
    private ImageView ivActionBack, ivActionSearch, ivActionMenuBar;

    @Override
    public void setContentView(int layoutResID) {
        DrawerLayout fullView = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_main_home_layout, null);
        FrameLayout activityContent = fullView.findViewById(R.id.fl_fragment_main_home);
        getLayoutInflater().inflate(layoutResID, activityContent, true);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN |
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.setContentView(fullView);
        initView();
    }

    private MainToolbarActivity(Context context) {
        sInstance = (MainToolbarActivity) context.getApplicationContext();
    }

    public static MainToolbarActivity getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new MainToolbarActivity(context);
        }
        return sInstance;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void initView() {
        tvMainTitle = findViewById(R.id.main_title_toolbar);
        ivActionBack = findViewById(R.id.iv_back_tool_bar);
        ivActionSearch = findViewById(R.id.iv_search_tool_bar);
        ivActionMenuBar = findViewById(R.id.iv_menu_bar);

        BottomNavigationView bottomNavigationView = findViewById(R.id.menu_root);
        bottomNavigationView.setOnNavigationItemSelectedListener(navLister);

        ivActionBack.setOnClickListener(this);
        ivActionSearch.setOnClickListener(this);
        ivActionMenuBar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.iv_back_tool_bar:
                finish();
                break;
            case R.id.iv_menu_bar:
                Toast.makeText(mContext, "menu", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_search_tool_bar:
                Toast.makeText(mContext, "Search", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    protected void setTitle(String title) {
        if (TextUtils.isEmpty(title)) {
            setDisplayTitle(false);
        } else {
            setDisplayTitle(true);
            tvMainTitle.setText(title);
        }
    }


    protected void setDisplayMenu(boolean value) {
        ivActionMenuBar.setVisibility(value ? View.VISIBLE : View.GONE);
    }

    protected void setDisplayBack(boolean value) {
        ivActionBack.setVisibility(value ? View.VISIBLE : View.GONE);
    }


    protected void setDisplaySearch(boolean value) {
        ivActionBack.setVisibility(value ? View.VISIBLE : View.GONE);
    }

    protected void setFragment(Fragment fragment) {
        super.setContentView(R.layout.activity_toolbar_layout);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl_fragment, fragment)
                .addToBackStack(null)
                .commit();
        initView();
    }

    protected void setFragment(android.app.Fragment fragment) {
        super.setContentView(R.layout.activity_toolbar_layout);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_fragment, fragment);
        transaction.commit();
        initView();
    }

    protected void setFragment(Fragment fragment, String tag) {
        super.setContentView(R.layout.activity_toolbar_layout);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl_fragment, fragment, tag)
                .addToBackStack(null)
                .commit();
        initView();
    }

    /**
     * Add more fragment
     *
     * @param fragment
     */
    protected void addFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl_fragment, fragment)
                .addToBackStack(null)
                .commit();
    }

    protected void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_fragment, fragment)
                .addToBackStack(null)
                .commit();
    }

    protected void removeFragment(String tag) {
        FragmentManager fm = getSupportFragmentManager();
        Fragment oldFragment = fm.findFragmentByTag(tag);
        fm.beginTransaction().remove(oldFragment).commitAllowingStateLoss();
        fm.popBackStack();
    }

    protected void setDisplayTitle(boolean value) {
        tvMainTitle.setVisibility(value ? View.VISIBLE : View.GONE);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navLister
            = menuItem -> {
        Fragment selectedFragment = null;

        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                selectedFragment = new NewFeedFragment();
                break;
            case R.id.nav_notify:
                selectedFragment = new NotifyFragment();
                break;
            case R.id.nav_profile:
                selectedFragment = new ProfileFragment();
                break;
            case R.id.nav_menu_option:
                selectedFragment = new SearchFragment();
                break;
        }
        assert selectedFragment != null;
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_fragment_main_home, selectedFragment).commit();
        return false;
    };

}

