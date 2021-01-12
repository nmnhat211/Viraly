package com.example.viralyapplication.repository.api

import com.example.viralyapplication.utility.Constrain
import com.example.viralyapplication.utility.SessionCookie
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private const val timeOut = 30L;
    private fun getBaseUrl() = Constrain.BASE_URL

    private fun getClient(url: String): Retrofit {
        val okHttpClient = OkHttpClient.Builder().apply {
            connectTimeout(timeOut, TimeUnit.SECONDS)
            readTimeout(timeOut, TimeUnit.SECONDS)
        }.build()
        return Retrofit.Builder().apply {
            baseUrl(url)
            addCallAdapterFactory(CoroutineCallAdapterFactory())
            addConverterFactory(GsonConverterFactory.create())
            client(okHttpClient.newBuilder().cookieJar(SessionCookie()).build())
        }.build()
    }
    private val client: Retrofit by lazy { getClient(getBaseUrl()) }
}