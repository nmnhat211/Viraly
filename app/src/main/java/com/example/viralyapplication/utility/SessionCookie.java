package com.example.viralyapplication.utility;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public class SessionCookie implements CookieJar {
    private List<Cookie> cookies;

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if (url.encodedPath().endsWith("login")) {
            this.cookies = new ArrayList<>(cookies);
            this.cookies.addAll(cookies);
            Log.e("addCookie", "" + cookies);
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        if (!url.encodedPath().endsWith("login") && cookies != null) {
            Log.e("loadForRequest: ", "" + cookies);
            return cookies;
        }
        return Collections.emptyList();
    }

}