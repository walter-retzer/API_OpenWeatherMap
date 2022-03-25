package com.wdretzer.api_weathermap.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.wdretzer.api_weathermap.R
import com.wdretzer.api_weathermap.pesquisa.SearchActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        getSupportActionBar()?.hide()

        // Iniciando as Telas de Boas Vindas:
        Handler().postDelayed({
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }, 6000)
    }
}
