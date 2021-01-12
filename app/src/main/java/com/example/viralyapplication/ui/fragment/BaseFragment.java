package com.example.viralyapplication.ui.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.example.viralyapplication.R;
import com.example.viralyapplication.ui.event.BaseEvent;

import java.util.ArrayList;

public class BaseFragment extends Fragment implements OnClickListener {
    protected final String TAG = this.getClass().getSimpleName();
    protected Context mContext;
    private AlertDialog alertDialog;
    protected Dialog mProgress;
    private static final String RETAINED_FRAGMENT_TAG = "retained_fragment";


    @Override
    public void onDestroy() {
        super.onDestroy();
        dismissProgressDialog();
    }


    public void showProgressDialog() {
        if (mProgress == null) {
            mProgress = new Dialog(this.getActivity(), android.R.style.Theme_Translucent_NoTitleBar);
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

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

    }

    protected void registerBroadcast(BroadcastReceiver broadcastReceiver,
                                     ArrayList<String> actions) {
        IntentFilter intentFilter = new IntentFilter();
        for (String action : actions) {
            intentFilter.addAction(action);
        }
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver((broadcastReceiver),
                intentFilter);
    }

    protected void registerBroadcast(BroadcastReceiver broadcastReceiver,
                                     String action) {
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver((broadcastReceiver),
                new IntentFilter(action));
    }

    protected void unregisterBroadcast(BroadcastReceiver broadcastReceiver) {
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(broadcastReceiver);
    }

    protected void sendBroadcast(String action) {
        LocalBroadcastManager broadcaster = LocalBroadcastManager.getInstance(getActivity());
        Intent intent = new Intent(action);
        broadcaster.sendBroadcast(intent);
    }

    protected void sendBroadcast(Intent intent) {
        LocalBroadcastManager broadcaster = LocalBroadcastManager.getInstance(getActivity());
        broadcaster.sendBroadcast(intent);
    }

    public void showKeyBoard() {
    }

    protected void showKeyboard(boolean show) {
        if (show)
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        else
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    protected void showKeyboard(FragmentActivity activity, boolean show) {
        if (show)
            activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        else
            activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public void showKeyBoard(View view, boolean isShow) {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (isShow) {
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        } else {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            view.clearFocus();
        }
    }

    public void clearScreenData() {
    }

    protected boolean isShowingDialog() {
        return alertDialog != null && alertDialog.isShowing();
    }

    public interface OnKeyboardVisibilityListener {
        void onVisibilityChanged(boolean visible);
    }

    protected void setKeyboardVisibilityListener(final OnKeyboardVisibilityListener onKeyboardVisibilityListener) {
        final View parentView = ((ViewGroup) getActivity().findViewById(android.R.id.content)).getChildAt(0);
        parentView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            private boolean alreadyOpen;
            private final int defaultKeyboardHeightDP = 100;
            private final int EstimatedKeyboardDP = defaultKeyboardHeightDP + (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ? 48 : 0);
            private final Rect rect = new Rect();

            @Override
            public void onGlobalLayout() {
                int estimatedKeyboardHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, EstimatedKeyboardDP, parentView.getResources().getDisplayMetrics());
                parentView.getWindowVisibleDisplayFrame(rect);
                int heightDiff = parentView.getRootView().getHeight() - (rect.bottom - rect.top);
                boolean isShown = heightDiff >= estimatedKeyboardHeight;

                if (isShown == alreadyOpen) {
                    Log.i("Keyboard state", "Ignoring global layout change...");
                    return;
                }
                alreadyOpen = isShown;
                onKeyboardVisibilityListener.onVisibilityChanged(isShown);
            }
        });
    }

    protected void scrollToTarget(View scrollView, View target) {
        if (scrollView == null) return;
        int[] location = new int[2];
        target.getLocationOnScreen(location);
        if (scrollView.getClass() == NestedScrollView.class) {
            ((NestedScrollView) scrollView).smoothScrollTo(location[0], location[1]);
        } else {
            scrollView.scrollTo(location[0], location[1]);
        }
    }

    protected boolean checkFilterName(BaseEvent event){
        return TAG.equalsIgnoreCase(event.getFilterName());
    }

}

