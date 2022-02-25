package com.wdretzer.api_weathermap.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {

    fun build(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .build()
    }
}
