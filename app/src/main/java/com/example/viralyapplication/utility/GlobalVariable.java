package com.example.viralyapplication.utility;

import java.util.regex.Pattern;

public class GlobalVariable {
    private static GlobalVariable mGlobalVariable;

    public static GlobalVariable getInstance() {
        if (mGlobalVariable == null) {
            mGlobalVariable = new GlobalVariable();
        }
        return mGlobalVariable;
    }

    public boolean isValidEmail(String email) {
        Pattern mPat = Pattern.compile(Constrain.EMAIL_REGEX);
        if (email == null) {
            return false;
        }
        return !mPat.matcher(email).matches();
    }

    public boolean isValidPassword(String password) {
        Pattern mPat = Pattern.compile(Constrain.PASSWORD_REGEX);
        if (password == null) {
            return false;
        }
        return !mPat.matcher(password).matches();
    }

}
