package com.zrz.android.swcurrency.model.network

import okhttp3.Request

class NetworkManagerImpl : NetworkManager {

    private val client = NetworkClient.getClient()

    override fun doGetRequest(url: String): String {
        val request = Request.Builder()
            .url("http://data.fixer.io/api/latest?access_key=75b0220fe8dfd1ac9bd398d89e65f599")
            .get()
            .build()
        val response = client.newCall(request).execute()
        return response.body!!.string()
    }
}