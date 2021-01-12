package com.example.viralyapplication.repository

import android.app.Application
import com.example.viralyapplication.utility.NetworkUtils

open class BaseRepository(application: Application) {
    private val STATUS_OK = "ok"
    private val STATUS_SUCCESS = "SUCCESS"
    protected val mApplication: Application = application

    suspend fun <T> apiCall() {
        if (!NetworkUtils.isNetworkConnected(mApplication)) {
            return
        }
    }
}