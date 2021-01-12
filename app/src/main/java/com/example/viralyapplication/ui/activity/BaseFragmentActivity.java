package com.example.viralyapplication.ui.activity;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.fragment.app.FragmentActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.viralyapplication.R;
import com.example.viralyapplication.ui.event.BaseEvent;

public abstract class BaseFragmentActivity extends FragmentActivity implements View.OnClickListener {
    protected final String TAG = this.getClass().getSimpleName();
    protected Dialog mProgress;
    protected Context mContext;

    public void showProgressDialog() {
        if (mProgress == null) {
            mProgress = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);
            mProgress.setContentView(R.layout.progressing_dialog_layout);
            mProgress.setCancelable(false);
        }
        mProgress.show();
    }

    public void dismissProgressDialog() {
        if (mProgress != null && mProgress.isShowing()) {
            mProgress.dismiss();
            mProgress = null;
        }
    }
    protected void registerBroadcast(BroadcastReceiver broadcastReceiver, String action) {
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver,
                new IntentFilter(action));
    }

    protected void unregisterBroadcast(BroadcastReceiver broadcastReceiver) {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
    }

    protected void sendBroadcast(String action) {
        LocalBroadcastManager broadcaster = LocalBroadcastManager.getInstance(this);
        Intent intent = new Intent(action);
        broadcaster.sendBroadcast(intent);
    }

    private void showKeyBoard(View view, boolean isShow) {
        view.requestFocus();
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (isShow) {
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        } else {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            view.clearFocus();
        }
    }

    protected boolean checkFilterName(BaseEvent event){
        return TAG.equalsIgnoreCase(event.getFilterName());
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() == 1){
            finish();
        } else {
            super.onBackPressed();
        }
    }
}
