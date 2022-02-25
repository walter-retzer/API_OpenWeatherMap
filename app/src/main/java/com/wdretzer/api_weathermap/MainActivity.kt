package com.wdretzer.api_weathermap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import com.wdretzer.api_weathermap.viewmodel.ApiViewModel


class MainActivity : AppCompatActivity() {

    private val viewModelApi: ApiViewModel by viewModels()
    private val weather_city: TextView by lazy { findViewById(R.id.address) }
    private val weather_description: TextView by lazy { findViewById(R.id.status) }
    private val weather_temp: TextView by lazy { findViewById(R.id.temp) }
    private val weather_tempMin: TextView by lazy { findViewById(R.id.temp_min) }
    private val weather_tempMax: TextView by lazy { findViewById(R.id.temp_max) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getSupportActionBar()?.hide();
        chamadas()
        rendirizacoes()
    }

    private fun rendirizacoes() {
        observarApi()
    }

    private fun observarApi() {
        viewModelApi.error.observe(this) {
            if (it) {
                weather_city.text = "Falhouuu!!"
                Toast.makeText(this, "Falha!!", Toast.LENGTH_LONG).show()
            }

        }
        viewModelApi.city.observe(this) {
            weather_city.text = "$it"
        }

        viewModelApi.tempData.observe(this) {
            weather_temp.text = "${it.temp}°C"
            weather_tempMax.text = "Max Temp: ${it.tempMax}°C"
            weather_tempMin.text = "Min Temp: ${it.tempMin}°C"
        }
    }

    private fun chamadas() {
        viewModelApi.getData("sorocaba")
    }
}
