package com.example.viralyapplication.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.viralyapplication.R;
import com.example.viralyapplication.repository.api.LoginApi;
import com.example.viralyapplication.repository.model.RegisterAccountModel;
import com.example.viralyapplication.repository.model.UserModel;
import com.example.viralyapplication.utility.Constrain;
import com.example.viralyapplication.utility.NetworkProfile;
import com.example.viralyapplication.utility.Utils;
import com.google.android.material.textfield.TextInputLayout;

import kotlinx.coroutines.scheduling.Task;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends ToolbarActivity {
    private Button btnCreateAccount, btnTextSignUp;
    private RelativeLayout rlParen;
    private EditText edtUserName, edtDisplayName, edtEmailAddress, edtPassword;
    private TextInputLayout txtInputUserName, txtInputDisplayName, txtInputEmailAddress, txtInputPassword;
    private boolean isUsernameEmpty = true, isNameEmpty = true, isMailEmpty = true, isPasswordEmpty = true;
    private String username = "", name = "", mail = "", password = "", text = "is";

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_layout);
        loadView();
        initEvent();
        EmptyEditText();
    }

    private void initView() {
        setDisplayBack(true);
        setDisplayTitle(false);
        setTitleToolbar(getString(R.string.title_sign_up));

        btnTextSignUp = findViewById(R.id.button_of_bottom_welcome_screen);
        btnCreateAccount = findViewById(R.id.button_create_account);
        edtUserName = findViewById(R.id.edit_text_name);
        edtDisplayName = findViewById(R.id.edit_text_display_name);
        edtEmailAddress = findViewById(R.id.edit_text_email);
        edtPassword = findViewById(R.id.edit_text_password);

        txtInputUserName = findViewById(R.id.text_input_name);
        txtInputDisplayName = findViewById(R.id.text_input_display_name);
        txtInputEmailAddress = findViewById(R.id.text_input_mail);
        txtInputPassword = findViewById(R.id.text_input_password);

        rlParen = findViewById(R.id.root_signup);

        btnTextSignUp.setOnClickListener(this);
        btnCreateAccount.setOnClickListener(this);
        edtUserName.addTextChangedListener(edtWatcher);
        edtDisplayName.addTextChangedListener(edtWatcher);
        edtEmailAddress.addTextChangedListener(edtWatcher);
        edtPassword.addTextChangedListener(edtWatcher);
    }

    private void loadView() {
        initView();
        edtEmailAddress.setText("");
        edtPassword.setText("");
        btnTextSignUp.setText(getText(R.string.title_sign_in));
        Utils.setVisible(btnCreateAccount, false);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initEvent() {
//        Remove focus for EditText
        rlParen.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if (edtUserName.isFocused() || edtPassword.isFocused()
                        || edtEmailAddress.isFocused() || edtDisplayName.isFocused()) {
                    Rect outRect = new Rect();
                    edtUserName.getGlobalVisibleRect(outRect);
                    edtPassword.getGlobalVisibleRect(outRect);
                    edtEmailAddress.getGlobalVisibleRect(outRect);
                    edtDisplayName.getGlobalVisibleRect(outRect);
                    if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                        edtUserName.clearFocus();
                        edtPassword.clearFocus();
                        edtEmailAddress.clearFocus();
                        edtDisplayName.clearFocus();
                        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }
                }
            }
            return false;
        });
    }


    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.button_of_bottom_welcome_screen:
                Utils.goToSigInActivity(mContext);
                finish();
                break;
            case R.id.button_create_account:
                handleOnClickButtonCreate();
                break;
        }
    }

    private TextWatcher edtWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (!isUsernameEmpty && !isNameEmpty && !isMailEmpty && !isPasswordEmpty) {
                Utils.setVisible(btnCreateAccount, true);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private void createAccount(String mail,
                               String username,
                               String name,
                               String password) {
        showProgressDialog();
        LoginApi createAccount = NetworkProfile.getRetrofitInstance().create(LoginApi.class);
        RegisterAccountModel model = new RegisterAccountModel(mail, username, name, password);
        Call<UserModel> callApi = createAccount.registerUser(model);
        callApi.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                dismissProgressDialog();
                if (response.code() == Constrain.IS_SUCCESS) {
                    Utils.sendBroadCastLogin(SignUpActivity.this, mail, password);
                    Toast.makeText(SignUpActivity.this, getString(R.string.create_successfully), Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    if (response.code() == Constrain.IS_BAB_REQUEST) {
                        Utils.showAlertDialogOk(SignUpActivity.this,
                                getString(R.string.server_error_txt),
                                getString(R.string.invalid_txt),
                                (DialogInterface.OnClickListener) (dialogInterface, i) -> dialogInterface.dismiss());
                    } else {
                        Toast.makeText(SignUpActivity.this, R.string.server_error, Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                dismissProgressDialog();
                Toast.makeText(SignUpActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handleOnClickButtonCreate() {
        mail = edtEmailAddress.getText().toString().trim();
        username = edtUserName.getText().toString().trim();
        name = edtDisplayName.getText().toString().trim();
        password = edtPassword.getText().toString().trim();
        if (!Utils.isValidEmail(mail)) {
            Utils.showAlertDialogOk(SignUpActivity.this,
                    getString(R.string.error_txt),
                    getString(R.string.invalid_email_txt), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
        } else {
            createAccount(mail, username, name, password);
        }
    }


    private void EmptyEditText() {
        edtUserName.setOnFocusChangeListener((view, hasFocus) -> {
            if (!hasFocus) {
                if (edtUserName.getText().toString().trim().length() == 0) {
                    txtInputUserName.setError("");
                    isUsernameEmpty = true;
                }
            } else {
                txtInputUserName.setError(null);
                isUsernameEmpty = false;
            }
        });

        edtDisplayName.setOnFocusChangeListener((view, hasFocus) -> {
            if (!hasFocus) {
                if (edtDisplayName.getText().toString().trim().length() == 0) {
                    txtInputDisplayName.setError("");
                    isNameEmpty = true;
                }
            } else {
                txtInputDisplayName.setError(null);
                isNameEmpty = false;
            }
        });

        edtEmailAddress.setOnFocusChangeListener((view, hasFocus) -> {
            if (!hasFocus) {
                if (edtEmailAddress.getText().toString().trim().length() == 0) {
                    txtInputEmailAddress.setError("");
                    isMailEmpty = true;
                }
            } else {
                txtInputEmailAddress.setError(null);
                isMailEmpty = false;
            }
        });

        edtPassword.setOnFocusChangeListener((view, hasFocus) -> {
            if (!hasFocus) {
                if (edtPassword.getText().toString().trim().length() == 0) {
                    txtInputPassword.setError("");
                    isPasswordEmpty = true;
                }
            } else {
                txtInputPassword.setError(null);
                isPasswordEmpty = false;
            }
        });

    }

}