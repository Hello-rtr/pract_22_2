package com.example.myapplication

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var first_spinner: Spinner
    private lateinit var second_spinner: Spinner
    private lateinit var rez: TextView
    private lateinit var sum: EditText
    private lateinit var button1: Button
    private var choice1: String = ""
    private var choice2: String = ""

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sum = findViewById(R.id.editText_1)
        first_spinner = findViewById(R.id.first_spinner)
        second_spinner = findViewById(R.id.second_spinner)
        button1 = findViewById(R.id.button_1)
        rez = findViewById(R.id.rez_)
        val currencies = arrayOf("USD", "EUR", "RUB", "GBP", "JPY")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        first_spinner.adapter = adapter
        second_spinner.adapter = adapter

        button1.setOnClickListener {
            Log.d("MyLog", "Button clicked")
            choice2 = first_spinner.selectedItem?.toString() ?: ""
            choice1 = second_spinner.selectedItem?.toString() ?: ""
            Log.d("MyLog", "Choice1: $choice1, Choice2: $choice2")

            if (choice1.isNotEmpty() && choice2.isNotEmpty() && choice1 != choice2) {
                val apy = "0bd72f77e5a156832cb9fb58fb58e2d3"
                val url = "https://currate.ru/api/?get=rates&pairs=$choice1$choice2&key=$apy"
                val queue = Volley.newRequestQueue(this)

                val stringRequest = StringRequest(
                    Request.Method.GET,
                    url,
                    { response ->
                        try {
                            val obj = JSONObject(response)
                            val well = obj.getJSONObject("data").getString("$choice1$choice2").toDouble()
                            var otv = sum.text.toString()
                            rez.text = (well * otv.toDouble()).toString()
                        } catch (e: Exception) {
                            Log.e("MyLog", "JSON error: $e")
                            rez.text = "Ошибка при обработке данных"
                        }
                    },
                    { error ->
                        Log.e("MyLog", "Volley error: $error")
                        rez.text = "Ошибка запроса"
                    }
                )
                queue.add(stringRequest)
            } else {
                val sn = Snackbar.make(findViewById(android.R.id.content), "Выберите разные валюты", Snackbar.LENGTH_LONG)
                sn.setActionTextColor(Color.RED)
                sn.show()
            }
        }
    }
}
