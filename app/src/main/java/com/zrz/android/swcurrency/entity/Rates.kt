package com.zrz.android.swcurrency.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Rates(val base: String, val rates: Map<String, Double>)