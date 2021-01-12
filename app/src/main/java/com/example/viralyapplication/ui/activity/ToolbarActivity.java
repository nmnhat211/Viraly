package com.example.viralyapplication.ui.activity;

import android.app.FragmentTransaction;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.example.viralyapplication.R;


public class ToolbarActivity extends BaseFragmentActivity{

    private static ToolbarActivity sInstance = null;
    protected Context mContext;
    private TextView tvMainTitle;
    private ImageView ivMainBack;

    public void setContentView(int layoutResID) {
        DrawerLayout fullView = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_toolbar_layout, null);
        FrameLayout activityContent = fullView.findViewById(R.id.fl_fragment);
        getLayoutInflater().inflate(layoutResID, activityContent, true);
        super.setContentView(fullView);
        initView();
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
     * @param fragment
     */
    protected void addFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl_fragment, fragment)
                .addToBackStack(null)
                .commit();
    }

    protected void replaceFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_fragment, fragment)
                .addToBackStack(null)
                .commit();
    }

    protected void removeFragment(String tag){
        FragmentManager fm = getSupportFragmentManager();
        Fragment oldFragment = fm.findFragmentByTag(tag);
        fm.beginTransaction().remove(oldFragment).commitAllowingStateLoss();
        fm.popBackStack();
    }



    private void initView(){
        tvMainTitle = findViewById(R.id.main_title_toolbar);
        ivMainBack = findViewById(R.id.iv_back_tool_bar);

        ivMainBack.setOnClickListener(this);
    }

    protected void setDisplayTitle(boolean value){
        tvMainTitle.setVisibility(value ? View.VISIBLE : View.GONE);
    }

    protected void setDisplayBack(boolean value){
        ivMainBack.setVisibility(value ? View.VISIBLE : View.GONE);
    }

    protected void setTitleToolbar(String title){
        tvMainTitle.setText(title);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.iv_back_tool_bar) {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

