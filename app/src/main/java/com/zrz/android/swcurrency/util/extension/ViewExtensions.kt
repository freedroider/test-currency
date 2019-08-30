package com.zrz.android.swcurrency.util.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.AppCompatEditText

fun ViewGroup.inflateView(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View =
    LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)

fun AppCompatEditText.text() = this.text.toString()

fun AppCompatEditText.clear() = this.text?.clear()