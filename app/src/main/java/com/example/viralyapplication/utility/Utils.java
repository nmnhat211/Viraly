package com.example.viralyapplication.utility;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.viralyapplication.R;
import com.example.viralyapplication.repository.model.UserModel;
import com.example.viralyapplication.ui.activity.SignInActivity;
import com.example.viralyapplication.ui.activity.SignUpActivity;
import com.example.viralyapplication.ui.activity.WelcomeActivity;
import com.example.viralyapplication.ui.fragment.NewFeedFragment;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import static android.provider.Settings.System.getString;

public class Utils {
    public static Context mContext;
    private DialogListener mDialogListener;
    public static String mUsername = "";
    public static String mPassword = "";

    public static void showKeyBoard(View view, boolean isShow) {
        view.requestFocus();
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (isShow) {
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        } else {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            view.clearFocus();
        }
    }

    public static void goToSigUpActivity(Context context) {
        Intent intent = new Intent(context, SignUpActivity.class);
        context.startActivity(intent);
    }

    public static void goToSigInActivity(Context context) {
        Intent intent = new Intent(context, SignInActivity.class);
        context.startActivity(intent);
    }

    public static void goToNewFeedFragment(Context context, UserModel userModel) {
        Intent intent = new Intent(context, SignInActivity.class);
        intent.putExtra(NewFeedFragment.KEY_USER_DATA, userModel);
        context.startActivity(intent);
    }

    public static void setTextViewDrawableColor(TextView textView, int color) {
        for (Drawable drawable : textView.getCompoundDrawables()) {
            if (drawable != null) {
                drawable.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(textView.getContext(), color), PorterDuff.Mode.SRC_IN));
            }
        }
    }

    public static boolean isNull(String string) {
        return string.isEmpty() || string.equals("null");
    }


    public static String isError(int status) {
        String errorMessages = "null";
        switch (status) {
            case Constrain.IS_SUCCESS:
                break;
            case Constrain.IS_FORBIDDEN:
                errorMessages = Resources.getSystem().getString(R.string.account_baned_text);
                break;
            default:
                return errorMessages;
        }
        return errorMessages;
    }

    public static String convertDate(String content) {
        String covertDate = "";
        Date date;

        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("MM/dd/yyyy");
        try {
            date = input.parse(content);
            covertDate = output.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return covertDate;
    }

    public static String convertFloat2f(float context) {
        DecimalFormat format = new DecimalFormat("#.00");
        return format.format(context);
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public void deleteAlert(Context context, String message, String title, int position, String id, DialogListener dialogListener) {
        mDialogListener = dialogListener;
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView tvDeleteTitle = dialog.findViewById(R.id.tv_title_dialog);
        TextView tvDeleteMessage = dialog.findViewById(R.id.tv_content_dialog);
        TextView tvCancel = dialog.findViewById(R.id.tv_cancel);
        Button btnPositive = dialog.findViewById(R.id.btn_delete);
        btnPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialogListener.onAcceptClickListener(dialog, position, id);
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        tvDeleteTitle.setText(title);
        tvDeleteMessage.setText(message);
        dialog.show();
    }

//    public static void showAlertDialog(Context context, String title, String message,
//                                       DialogInterface.OnClickListener positiveListener, DialogInterface.OnClickListener negativeListener) {
//        if((context instanceof Activity && ((Activity) context).isFinishing())
//                || TextUtils.isEmpty(message)) return;
//        Typeface typefaceWithFont = Typeface.createFromAsset(getAssets(), "fonts/gilroy_medium.ttf");
//        AlertDialog.Builder builder =  new AlertDialog.Builder(new ContextThemeWrapper(context, R.style.Theme_AppCompat_Light_Dialog))
//                .setTitle(typeface(typefaceWithFont, title))
//                .setMessage(message)
//                .setPositiveButton(getString(R.string.ok_txt), positiveListener)
//                .setNegativeButton(R.string.cancel_txt, negativeListener);
//        builder.setCancelable(false);
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//        alertDialog.getWindow().getAttributes();
//        TextView textView = (TextView) alertDialog.findViewById(android.R.id.message);
//        Button btnPositive = (Button) alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
//        Button btnNegative = (Button) alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
//    }


    public static void showAlertDialog(Context context, String title, String content) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(content)

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public static void showAlertDialogOk(Context context, String title, String content, DialogInterface.OnClickListener negativeListener) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(content)
                .setCancelable(false)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Whatever...
                    }
                }).show();
    }

    public static void sendBroadCastLogin(Context context, String mail, String password) {
        Intent intent = new Intent(context, SignInActivity.class);
        intent.putExtra(SignInActivity.KEY_USERNAME, mail);
        intent.putExtra(SignInActivity.KEY_PASSWORD, password);
        context.sendBroadcast(intent, SignInActivity.KEY_CREATE_ACCOUNT_DONE);
    }

    public static void saveRememberAccount(Context context, Boolean value,String username, String password) {
        SharedPreferences pref = context.getSharedPreferences(Constrain.PREFS_NAME, Context.MODE_PRIVATE);
        if (value) {
            pref.edit()
                    .putBoolean(Constrain.KEY_REMEMBER_ME, true)
                    .putString(Constrain.KEY_USERNAME, username)
                    .putString(Constrain.KEY_PASSWORD, password)
                    .apply();
        } else {
            pref.edit()
                    .clear()
                    .apply();

        }
    }

    public static boolean isNotWelcome(Context context) {
        SharedPreferences pref = context.getSharedPreferences(Constrain.PREFS_NAME, Context.MODE_PRIVATE);
        return pref.getBoolean(Constrain.KEY_REMEMBER_ME, false);
    }

    public static String getUsername(Context context){
        SharedPreferences pref = context.getSharedPreferences(Constrain.PREFS_NAME, Context.MODE_PRIVATE);
        mUsername = pref.getString(Constrain.KEY_USERNAME, "null");
        return mUsername;
    }

    public static String getPassword(Context context){
        SharedPreferences pref = context.getSharedPreferences(Constrain.PREFS_NAME, Context.MODE_PRIVATE);
            mPassword = pref.getString(Constrain.KEY_PASSWORD, "null");
        return mPassword;
    }

    public static void setVisible(View view, boolean visible){
        view.setAlpha(1f);
        view.setEnabled(true);
        if (!visible){
            view.setAlpha(0.5f);
            view.setEnabled(false);
        }
    }

}
