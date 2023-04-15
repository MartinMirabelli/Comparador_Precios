package com.example.comparadorpreciosmirabelli

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.google.android.material.progressindicator.BaseProgressIndicator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //creo una val spinner y busco su id
        val spinner = findViewById<Spinner>(R.id.sp1)
        //busco en los recursos string "unidades" que contiene los val posibles"
        //val unidades = listOf<String>("Unidades","Metro","Kilogramo")  // ej. cableado (no va...)
        val unidades = resources.getStringArray(R.array.unidades)
        //creo un adaptador apropiado para el spinner
        val adaptador = ArrayAdapter(this,android.R.layout.simple_spinner_item,unidades)
        // seteo la prop. adapter del spinner con el adaptador creado
        spinner.adapter = adaptador


        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?,
                                        p1: View?,
                                        index: Int,
                                        p3: Long) {
               Toast.makeText(this@MainActivity,unidades[index],Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

    }

}