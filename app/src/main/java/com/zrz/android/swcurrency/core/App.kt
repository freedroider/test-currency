package com.zrz.android.swcurrency.core

import android.app.Application
import com.zrz.android.swcurrency.core.di.AppDI

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AppDI.setup(this)
    }
}