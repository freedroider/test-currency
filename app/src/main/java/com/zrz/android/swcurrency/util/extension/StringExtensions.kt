package com.zrz.android.swcurrency.util.extension

import com.zrz.android.swcurrency.BuildConfig.APPLICATION_ID
import com.zrz.android.swcurrency.feature.rate.RateActivity.Companion.BUNDLE_KEY
import com.zrz.android.swcurrency.feature.rate.RateActivity.Companion.INTENT_KEY

fun String.asBundleKey() = "$APPLICATION_ID$BUNDLE_KEY$this"

fun String.asIntentKey() = "$APPLICATION_ID$INTENT_KEY$this"