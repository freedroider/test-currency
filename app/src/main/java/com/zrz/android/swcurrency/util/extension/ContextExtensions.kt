package com.zrz.android.swcurrency.util.extension

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity

inline fun <reified A> AppCompatActivity.startNewActivity(
    intentKey: String,
    vararg extras: Pair<String, Parcelable>
) where A : AppCompatActivity {
    val intent = Intent(this, A::class.java)
    val list = listOf(*extras)
    if (list.isNotEmpty()) {
        val bundle = Bundle()
        list.forEach { (key, extra) ->
            bundle.putParcelable(key.asBundleKey(), extra)
        }
        intent.putExtra(intentKey.asIntentKey(), bundle)
    }
    startActivity(intent)
}