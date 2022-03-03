package com.wdretzer.api_weathermap.data

import com.wdretzer.api_weathermap.model.WeatherData
import retrofit2.http.*

interface Api {

    //http://api.openweathermap.org/data/2.5/weather?q=sorocaba,br&units=metric&APPID=valordotokendasuaapi
    @GET("weather?units=metric&APPID=valordotokendasuaapi")
    suspend fun getWeatherData(@Query("q") city: String): WeatherData

    companion object {
        val api: Api by lazy {
            RetrofitFactory.build()
                .create(Api::class.java)
        }
    }
}
