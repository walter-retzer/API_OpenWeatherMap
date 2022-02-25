package com.wdretzer.api_weathermap.model

import com.google.gson.annotations.SerializedName

data class WeatherData(
    @SerializedName("coord") val coord: Coord,
    @SerializedName("weather") val weather: List<Weather>,
    @SerializedName("base") val base: String,
    @SerializedName("main") val main: TemperatureData,
    @SerializedName("visibility") val visibility: Int,
    @SerializedName("wind") val wind: Wind,
    @SerializedName("clouds") val clouds: Clouds,
    @SerializedName("dt") val dt: Int,
    @SerializedName("sys") val sys: Sys,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("cod") val cod: Int
)

data class Weather(
    @SerializedName("id") val id: Int,
    @SerializedName("main") val main: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String
)

data class Sys(
    @SerializedName("type") val type: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("message") val message: Double,
    @SerializedName("country") val country: String,
    @SerializedName("sunrise") val sunrise: Int,
    @SerializedName("sunset") val sunset: Int
)

data class Coord(
    @SerializedName("lon") val lon: Double,
    @SerializedName("lat") val lat: Double
)

data class TemperatureData(
    @SerializedName("temp") val temp: Double,
    @SerializedName("pressure") val pressure: Int,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("temp_min") val tempMin: Double,
    @SerializedName("temp_max") val tempMax: Double
)



data class Clouds(
    @SerializedName("all") val all: Int
)

data class Wind(
    @SerializedName("speed") val speed: Double,
    @SerializedName("deg") val deg: Int
)

//data class WeatherData(
//
//    @field:SerializedName("dt")
//    val dt: Int? = null,
//
//    @field:SerializedName("coord")
//    val coord: Coord? = null,
//
//    @field:SerializedName("visibility")
//    val visibility: Int? = null,
//
//    @field:SerializedName("weather")
//    val weather: List<WeatherItem?>? = null,
//
//    @field:SerializedName("name")
//    val name: String? = null,
//
//    @field:SerializedName("cod")
//    val cod: Int? = null,
//
//    @field:SerializedName("main")
//    val main: Main? = null,
//
//    @field:SerializedName("clouds")
//    val clouds: Clouds? = null,
//
//    @field:SerializedName("id")
//    val id: Int? = null,
//
//    @field:SerializedName("sys")
//    val sys: Sys? = null,
//
//    @field:SerializedName("base")
//    val base: String? = null,
//
//    @field:SerializedName("wind")
//    val wind: Wind? = null
//)
//
//data class Clouds(
//
//    @field:SerializedName("all")
//    val all: Int? = null
//)
//
//data class Coord(
//
//    @field:SerializedName("lon")
//    val lon: Double? = null,
//
//    @field:SerializedName("lat")
//    val lat: Double? = null
//)
//
//
//data class Main(
//
//    @field:SerializedName("temp")
//    val temp: Double? = null,
//
//    @field:SerializedName("temp_min")
//    val tempMin: Double? = null,
//
//    @field:SerializedName("humidity")
//    val humidity: Int? = null,
//
//    @field:SerializedName("pressure")
//    val pressure: Int? = null,
//
//    @field:SerializedName("temp_max")
//    val tempMax: Double? = null
//)
//data class Sys(
//
//    @field:SerializedName("country")
//    val country: String? = null,
//
//    @field:SerializedName("sunrise")
//    val sunrise: Int? = null,
//
//    @field:SerializedName("sunset")
//    val sunset: Int? = null,
//
//    @field:SerializedName("id")
//    val id: Int? = null,
//
//    @field:SerializedName("type")
//    val type: Int? = null,
//
//    @field:SerializedName("message")
//    val message: Double? = null
//)
//
//data class WeatherItem(
//
//    @field:SerializedName("icon")
//    val icon: String? = null,
//
//    @field:SerializedName("description")
//    val description: String? = null,
//
//    @field:SerializedName("main")
//    val main: String? = null,
//
//    @field:SerializedName("id")
//    val id: Int? = null
//)
//
//data class Wind(
//
//    @field:SerializedName("deg")
//    val deg: Int? = null,
//
//    @field:SerializedName("speed")
//    val speed: Double? = null
//)
