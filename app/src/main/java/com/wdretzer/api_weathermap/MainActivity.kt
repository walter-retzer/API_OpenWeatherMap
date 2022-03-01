package com.wdretzer.api_weathermap

import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import com.wdretzer.api_weathermap.viewmodel.ApiViewModel
import java.lang.Exception
import java.util.*


class MainActivity : AppCompatActivity() {

    val thunderstorm = "Thunderstorm"
    val drizzle = "Drizzle"
    val rain = "Rain"
    val snow = "Snow"
    val clear = "Clear"
    val clouds = "Clouds"
    val fewClouds = "few clouds"
    val scatteredClouds = "scattered clouds"
    val brokenClouds = "broken clouds"
    val overcastClouds = "overcast clouds"


    private val viewModelApi: ApiViewModel by viewModels()
    private val weather_city: TextView by lazy { findViewById(R.id.address) }
    private val weather_description: TextView by lazy { findViewById(R.id.status) }
    private val weather_temp: TextView by lazy { findViewById(R.id.temp) }
    private val weather_tempMin: TextView by lazy { findViewById(R.id.temp_min) }
    private val weather_tempMax: TextView by lazy { findViewById(R.id.temp_max) }
    private val weather_rain: TextView by lazy { findViewById(R.id.rain) }
    private val weather_pressure: TextView by lazy { findViewById(R.id.pressure) }
    private val weather_humidity: TextView by lazy { findViewById(R.id.humidity) }
    private val weather_wind: TextView by lazy { findViewById(R.id.wind) }
    private val weather_sunset: TextView by lazy { findViewById(R.id.sunset) }
    private val weather_sunrise: TextView by lazy { findViewById(R.id.sunrise) }
    private val current_date: TextView by lazy { findViewById(R.id.updated_at) }
    private val progress_bar: ProgressBar by lazy { findViewById(R.id.loader) }
    private val imagem: ImageView by lazy { findViewById(R.id.imagem) }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getSupportActionBar()?.hide()

        current_dateTime()
        chamadas()
        rendirizacoes()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun rendirizacoes() {
        observarApi()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun observarApi() {

        viewModelApi.loading.observe(this){
            progress_bar.isVisible = it

        }

        viewModelApi.error.observe(this) {
            if (it) {
                weather_city.text = "Falhouuu!!"
                Toast.makeText(this, "Falha!!", Toast.LENGTH_LONG).show()
            }
        }

        viewModelApi.city.observe(this) {
            weather_city.text = "$it"
            weather_description.text = "$it"
            progress_bar.isVisible = false
        }

        viewModelApi.weather.observe(this) {

            weather_description.text = "${it.description}"

            if (it.main == rain) weather_rain.isVisible = true

            when(it.description){
                fewClouds -> imagem.setImageResource(R.drawable.fewclouds)
                scatteredClouds -> imagem.setImageResource(R.drawable.scattered_clouds)
                brokenClouds -> imagem.setImageResource(R.drawable.broken_clouds)
                overcastClouds -> imagem.setImageResource(R.drawable.clouds)
            }

            when(it.main){
                thunderstorm -> imagem.setImageResource(R.drawable.thunderstorm)
                drizzle -> imagem.setImageResource(R.drawable.drizzle)
                rain -> imagem.setImageResource(R.drawable.rain)
                snow -> imagem.setImageResource(R.drawable.rainandsnow)
                clear -> imagem.setImageResource(R.drawable.clearsky)
            }
       }

        viewModelApi.tempData.observe(this) {
            weather_temp.text = "${it.temp}°C"
            weather_tempMax.text = "Max Temp: ${it.tempMax}°C"
            weather_tempMin.text = "Min Temp: ${it.tempMin}°C"
            weather_pressure.text = "${it.pressure}hPa"
            weather_humidity.text = "${it.humidity}%"
        }

        viewModelApi.windData.observe(this) {
            weather_wind.text = "${it.speed} \nmeter/sec"
        }

        viewModelApi.rainData.observe(this){
            try{
                weather_rain.text = "Precipitation: ${it.rain}mm"
            } catch (e: Exception){
                weather_rain.isVisible = false
            }

        }

        viewModelApi.sysData.observe(this) {
            weather_sunset.text = getDateString(it.sunset.toLong())
            weather_sunrise.text = getDateString(it.sunrise.toLong())
        }
    }

    private fun chamadas() {
        viewModelApi.getData("sorocaba")
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun current_dateTime(): String? {
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
               return currentDate.also { current_date.text = it }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private val simpleDateFormat = SimpleDateFormat("dd MM yy, HH:mm:ss", Locale.CANADA_FRENCH)

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getDateString(time: Long): String = simpleDateFormat.format(time * 1000L)

}
