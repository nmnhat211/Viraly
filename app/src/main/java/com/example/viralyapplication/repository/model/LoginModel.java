package com.example.viralyapplication.repository.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginModel implements Serializable {
    @SerializedName("isAuthenticated")
    @Expose
    private Boolean isAuthenticated;
    @SerializedName("account")
    @Expose
    private UserModelLogin account;

    public Boolean getIsAuthenticated() {
        return isAuthenticated;
    }

    public void setIsAuthenticated(Boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }

    public UserModelLogin getAccount() {
        return account;
    }

    public void setAccount(UserModelLogin account) {
        this.account = account;
    }
}

