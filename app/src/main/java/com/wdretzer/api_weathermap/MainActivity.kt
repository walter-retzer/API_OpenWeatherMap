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


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getSupportActionBar()?.hide()

        current_dateTime()
        chamadas()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            rendirizacoes()
        }
    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun rendirizacoes() {
        observarApi()
    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun observarApi() {

        viewModelApi.loading.observe(this) {
            progress_bar.isVisible = it
        }

        viewModelApi.error.observe(this) {
            if (it) {
                weather_city.text = "Dados não encontrados!"
                Toast.makeText(this, "Falha na Requisição!!", Toast.LENGTH_LONG).show()
            }
        }

        viewModelApi.city.observe(this) {
            weather_city.text = "$it"
            weather_description.text = "$it"
            progress_bar.isVisible = false
        }

        viewModelApi.weather.observe(this) {

            // Deixa visivel a informação da preciptação, somente quando houver chuva,
            // Conforme doc da API, o dado da precipitação somente é enviado caso esteja ocorrendo chuva.
            if (it.main == rain) weather_rain.isVisible = true

            // Exibindo o icone da imagem da Previsão do Tempo de acordo com a informação recebida pela API:
            when (it.main) {
                thunderstorm -> imagem.setImageResource(R.drawable.thunderstorm)
                drizzle -> imagem.setImageResource(R.drawable.drizzle)
                rain -> imagem.setImageResource(R.drawable.rain)
                snow -> imagem.setImageResource(R.drawable.rainandsnow)
                mist -> imagem.setImageResource(R.drawable.mist)
                smoke -> imagem.setImageResource(R.drawable.black_smoke)
                haze -> imagem.setImageResource(R.drawable.haze)
                dust -> imagem.setImageResource(R.drawable.dust)
                fog -> imagem.setImageResource(R.drawable.fog)
                sand -> imagem.setImageResource(R.drawable.dust)
                ash -> imagem.setImageResource(R.drawable.ash)
                squall -> imagem.setImageResource(R.drawable.squalls)
                tornado -> imagem.setImageResource(R.drawable.cyclone)
            }

            // Exibindo o icone da imagem da Previsão do Tempo de acordo com a informação recebida pela API:
            when (it.description) {
                clearSky -> imagem.setImageResource(R.drawable.clearsky)
                fewClouds -> imagem.setImageResource(R.drawable.fewclouds)
                scatteredClouds -> imagem.setImageResource(R.drawable.scattered_clouds)
                brokenClouds -> imagem.setImageResource(R.drawable.broken_clouds)
                overcastClouds -> imagem.setImageResource(R.drawable.clouds)
            }

            // Inserindo as informações da descrição em português, conforme o id recebido pela API:
            when (it.id) {
                200 -> weather_description.text = id_200
                201 -> weather_description.text = id_201
                202 -> weather_description.text = id_202
                210 -> weather_description.text = id_210
                211 -> weather_description.text = id_211
                212 -> weather_description.text = id_212
                221 -> weather_description.text = id_221
                230 -> weather_description.text = id_230
                231 -> weather_description.text = id_231
                232 -> weather_description.text = id_232

                300 -> weather_description.text = id_300
                301 -> weather_description.text = id_301
                302 -> weather_description.text = id_302
                310 -> weather_description.text = id_310
                311 -> weather_description.text = id_311
                312 -> weather_description.text = id_312
                313 -> weather_description.text = id_313
                314 -> weather_description.text = id_314
                321 -> weather_description.text = id_321

                500 -> weather_description.text = id_500
                501 -> weather_description.text = id_501
                502 -> weather_description.text = id_502
                503 -> weather_description.text = id_503
                504 -> weather_description.text = id_504
                511 -> weather_description.text = id_511
                520 -> weather_description.text = id_520
                521 -> weather_description.text = id_521
                522 -> weather_description.text = id_522
                531 -> weather_description.text = id_531

                600 -> weather_description.text = id_600
                601 -> weather_description.text = id_601
                602 -> weather_description.text = id_602
                611 -> weather_description.text = id_611
                612 -> weather_description.text = id_612
                613 -> weather_description.text = id_613
                615 -> weather_description.text = id_615
                616 -> weather_description.text = id_616
                620 -> weather_description.text = id_620
                621 -> weather_description.text = id_621
                622 -> weather_description.text = id_622

                701 -> weather_description.text = id_701
                711 -> weather_description.text = id_711
                721 -> weather_description.text = id_721
                731 -> weather_description.text = id_731
                741 -> weather_description.text = id_741
                751 -> weather_description.text = id_751
                761 -> weather_description.text = id_761
                762 -> weather_description.text = id_762
                771 -> weather_description.text = id_771
                781 -> weather_description.text = id_781

                800 -> weather_description.text = id_800
                801 -> weather_description.text = id_801
                802 -> weather_description.text = id_802
                803 -> weather_description.text = id_803
                804 -> weather_description.text = id_804
            }
        }

        // atualização dos valores recebidos pela API:
        viewModelApi.tempData.observe(this) {
            weather_temp.text = "${it.temp}°C"
            weather_tempMax.text = "Max Temp: ${it.tempMax}°C"
            weather_tempMin.text = "Min Temp: ${it.tempMin}°C"
            weather_pressure.text = "${it.pressure}hPa"
            weather_humidity.text = "${it.humidity}%"
        }

        viewModelApi.windData.observe(this) {
            weather_wind.text = "${it.speed} \nmetros/seg"
        }

        viewModelApi.rainData.observe(this) {
            // O valor da precipitação poderá ser nulo, para o app não "quebrar", foi incluído um try/catch:
            try {
                weather_rain.text = "Precipitation: ${it.rain}mm"
            } catch (e: Exception) {
                weather_rain.isVisible = false
            }
        }

        viewModelApi.sysData.observe(this) {
            weather_sunset.text = getDateString(it.sunset.toLong())
            weather_sunrise.text = getDateString(it.sunrise.toLong())
        }
    }

    // Função para buscar as informações de uma determinada cidade:
    private fun chamadas() {
        viewModelApi.getData("rio de janeiro")
    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun current_dateTime(): String? {
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        return currentDate.also { current_date.text = "Última atualização: ${it}" }
    }


    @RequiresApi(Build.VERSION_CODES.N)
    private val simpleDateFormat = SimpleDateFormat("dd MM yy, HH:mm:ss", Locale.ENGLISH)


    @RequiresApi(Build.VERSION_CODES.N)
    private fun getDateString(time: Long): String = simpleDateFormat.format(time * 1000L)


    companion object {
        //Main - Informações da descrição do parametro main recebido pela API:
        val thunderstorm = "Thunderstorm"
        val drizzle = "Drizzle"
        val rain = "Rain"
        val snow = "Snow"
        val mist = "Mist"
        val smoke = "Smoke"
        val haze = "Haze"
        val dust = "Dust"
        val fog = "Fog"
        val sand = "Sand"
        val ash = "Ash"
        val squall = "Squalls"
        val tornado = "Tornado"
        val clearSky = "clear sky"
        val fewClouds = "few clouds"
        val scatteredClouds = "scattered clouds"
        val brokenClouds = "broken clouds"
        val overcastClouds = "overcast clouds"

        // Traduzindo para o Português-BR, as informações do item description recebido pela API:
        val id_200 = "Trovoada com Chuva Fraca"
        val id_201 = "Trovoada com Chuva"
        val id_202 = "Trovoada com Chuva Forte"
        val id_210 = "Trovoada Leve"
        val id_211 = "Trovoada"
        val id_212 = "Forte Tempestade"
        val id_221 = "Tempestade Irregular"
        val id_230 = "Trovoada com Leve Chuvisco"
        val id_231 = "Trovoada com Chuvisco"
        val id_232 = "Trovoada com Chuva Forte"

        val id_300 = "Chuvisco de Intensidade Leve"
        val id_301 = "Chuvisco"
        val id_302 = "Chuvisco de Forte Intensidade"
        val id_310 = "Garoa de intensidade leve"
        val id_311 = "Garoa"
        val id_312 = "Garoa de Forte Intensidade"
        val id_313 = "Chuva e Garoa"
        val id_314 = "Chuva Forte e Garoa"
        val id_321 = "Garoa Forte"

        val id_500 = "Chuva Leve"
        val id_501 = "Chuva Moderada"
        val id_502 = "Chuva de Forte Intensidade"
        val id_503 = "Chuva Muito Forte"
        val id_504 = "Chuva Extrema"
        val id_511 = "Chuva Congelante"
        val id_520 = "Chuva de Intensidade Leve"
        val id_521 = "Chuva Forte"
        val id_522 = "Chuva Intensa"
        val id_531 = "Chuva Intensa Irregular"

        val id_600 = "Pouca Neve"
        val id_601 = "Neve"
        val id_602 = "Neve Pesada"
        val id_611 = "Granizo"
        val id_612 = "Chuva de Garnizo Leve"
        val id_613 = "Chuva de Garnizo"
        val id_615 = "Chuva Leve e Neve"
        val id_616 = "Chuva e Neve"
        val id_620 = "Nevando Fraco"
        val id_621 = "Nevando"
        val id_622 = "Nevando Forte"

        val id_701 = "Névoa"
        val id_711 = "Fumaça"
        val id_721 = "Neblina"
        val id_731 = "Areia / Redemoinhos de Poeira"
        val id_741 = "Névoa"
        val id_751 = "Areia"
        val id_761 = "Poiera"
        val id_762 = "Cinza Vulcânica"
        val id_771 = "Rajadas de Vento"
        val id_781 = "Tornado"

        val id_800 = "Céu Limpo"
        val id_801 = "Algumas Nuvens"
        val id_802 = "Nuvens Dispersas"
        val id_803 = "Parcialmente Nublado"
        val id_804 = "Nublado"
    }
}
