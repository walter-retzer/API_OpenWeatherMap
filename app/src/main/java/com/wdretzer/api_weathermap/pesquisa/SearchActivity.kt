package com.wdretzer.api_weathermap.pesquisa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.wdretzer.api_weathermap.MainActivity
import com.wdretzer.api_weathermap.R

class SearchActivity : AppCompatActivity() {

    private val buttonSearch: Button by lazy { findViewById(R.id.btn_searchCity) }
    private val textSearch: TextInputEditText by lazy { findViewById(R.id.input_search)}
    private var searchWords: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        getSupportActionBar()?.hide()

        buttonSearch.setOnClickListener {

            if (textSearch.text?.isEmpty() == true) {
                Toast.makeText(this, "Digite uma palavra!!", Toast.LENGTH_SHORT).show()
            }

            if (textSearch.text?.isNotEmpty() == true) {
                searchWords = textSearch.text.toString()
                sendToSearch(searchWords)
            }
        }
    }

    private fun sendToSearch(search: String) {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("Search", search)

        }
        startActivity(intent)
    }
}
