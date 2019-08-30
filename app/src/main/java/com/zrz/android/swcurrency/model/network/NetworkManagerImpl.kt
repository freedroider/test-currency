package com.zrz.android.swcurrency.model.network

import android.util.Log
import okhttp3.Request
import okhttp3.Response

class NetworkManagerImpl : NetworkManager {

    private val client = NetworkClient.getClient()

    override fun doGetRequest(url: String): String {
        Log.d("ml", ">>> $url")
        val request = Request.Builder()
            .url(url)
            .get()
            .build()
        Log.d("ml", ">>>")
        Log.d("ml", ">>> $request")
        var response: Response? = null
        try{
            response = client.newCall(request).execute()
        } catch (e: Throwable) {
            Log.d("ml", "eee $e")
        }

        Log.d("ml", ">>> <<<")
        Log.d("ml", ">>> ${response!!.body!!.string()}")
        return response.body!!.string()
    }
}