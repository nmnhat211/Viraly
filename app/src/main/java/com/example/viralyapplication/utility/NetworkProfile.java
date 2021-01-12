package com.example.viralyapplication.utility;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkProfile {
    private static Retrofit sRetrofit;
    public static Retrofit getRetrofitInstance() {
        if (sRetrofit == null) {
            sRetrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(Constrain.BASE_URL)
                    .client(new OkHttpClient().newBuilder().cookieJar(new SessionCookie()).build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return sRetrofit;
    }

}
