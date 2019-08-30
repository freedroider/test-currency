package com.zrz.android.swcurrency.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrencyResult(val result: Double)