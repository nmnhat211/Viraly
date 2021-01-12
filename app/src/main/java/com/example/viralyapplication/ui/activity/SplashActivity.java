package com.example.viralyapplication.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.viralyapplication.utility.Utils;

public class SplashActivity extends AppCompatActivity {

    private Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                checkShowWelCome();
            }
        }, 3000);
    }

    private void checkShowWelCome(){
        boolean isNotShow = Utils.isNotWelcome(SplashActivity.this);
        if (isNotShow){
            Utils.goToSigInActivity(SplashActivity.this);
        }else {
            Intent intent = new Intent(SplashActivity.this, WelcomeActivity.class);
            startActivity(intent);
            finish();
        }
    }
}