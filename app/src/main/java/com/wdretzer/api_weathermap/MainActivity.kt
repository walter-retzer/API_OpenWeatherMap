package com.wdretzer.api_weathermap

import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.wdretzer.api_weathermap.viewmodel.ApiViewModel
import java.util.*


class MainActivity : AppCompatActivity() {

    private val viewModelApi: ApiViewModel by viewModels()
    private val weather_city: TextView by lazy { findViewById(R.id.address) }
    private val weather_description: TextView by lazy { findViewById(R.id.status) }
    private val weather_temp: TextView by lazy { findViewById(R.id.temp) }
    private val weather_tempMin: TextView by lazy { findViewById(R.id.temp_min) }
    private val weather_tempMax: TextView by lazy { findViewById(R.id.temp_max) }
    private val weather_pressure: TextView by lazy { findViewById(R.id.pressure) }
    private val weather_humidity: TextView by lazy { findViewById(R.id.humidity) }
    private val weather_wind: TextView by lazy { findViewById(R.id.wind) }
    private val weather_sunset: TextView by lazy { findViewById(R.id.sunset) }
    private val weather_sunrise: TextView by lazy { findViewById(R.id.sunrise) }
    private val current_date: TextView by lazy { findViewById(R.id.updated_at) }


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
        viewModelApi.error.observe(this) {
            if (it) {
                weather_city.text = "Falhouuu!!"
                Toast.makeText(this, "Falha!!", Toast.LENGTH_LONG).show()
            }

        }

        viewModelApi.city.observe(this) {
            weather_city.text = "$it"
            weather_description.text = "$it"
        }

        viewModelApi.weather.observe(this) {
            weather_description.text = "${it.description}"
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
