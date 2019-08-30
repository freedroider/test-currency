package com.zrz.android.swcurrency.model.network

interface NetworkManager {

    fun doGetRequest(url: String): String
}