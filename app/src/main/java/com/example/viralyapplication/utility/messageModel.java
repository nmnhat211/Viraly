package com.example.viralyapplication.utility;

import com.google.gson.annotations.Expose;
public class messageModel{
    private int statusCode;
    private String message;

    public messageModel() {
    }

    public int status() {
        return statusCode;
    }

    public String message() {
        return message;
    }
}
