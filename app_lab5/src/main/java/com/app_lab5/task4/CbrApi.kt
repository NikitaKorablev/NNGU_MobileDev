package com.app_lab5.task4

import retrofit2.Call
import retrofit2.http.GET

interface CbrApi {
    @GET("scripts/XML_daily.asp")
    fun getCurrencyRates(): Call<ValCurs>
}