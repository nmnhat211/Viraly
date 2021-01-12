package com.example.viralyapplication.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.viralyapplication.R;
import com.example.viralyapplication.utility.Utils;

public class WelcomeActivity extends BaseFragmentActivity {
    public static final String KEY_AUTO_LOGIN = "WelcomeActivity_key_auto_login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_layout);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN |
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        initView();
    }

    private void initView() {
        Button btnSignIn = findViewById(R.id.button_of_bottom_welcome_screen);
        Button btnSignUp = findViewById(R.id.button_create_account_welcome);
        btnSignIn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
        btnSignIn.setText(getText(R.string.title_sign_in));

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.button_of_bottom_welcome_screen:
                Utils.goToSigInActivity(this);
                break;
            case R.id.button_create_account_welcome:
                Utils.goToSigUpActivity(this);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}