package com.example.comparadorpreciosmirabelli

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import prueba.Producto
import prueba.TipoUnidad.*
import prueba.unidad
import prueba.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // cargo lista de tipo unidad con las unidades y caracteristicas de cada una...
        val uni_list = listOf<unidad>(
            unidad(getString(R.string.Metros),Longitud,1f ),
            unidad(getString(R.string.Centimetros),Longitud,100f ),
            unidad(getString(R.string.Milimetros),Longitud,1000f),
            unidad(getString(R.string.Pulgadas),Longitud,39.37007874015748f),  //esta unidad la agrego yo a modo de prueba

            unidad(getString(R.string.Kilogramos),Masa,1f),
            unidad(getString(R.string.Gramos),Masa,1000f),
            unidad(getString(R.string.Litros),Volumen,1f),
            unidad(getString(R.string.Centilitros),Volumen,100f),
            unidad(getString(R.string.cmCubicos),Volumen,1000f),
            unidad(getString(R.string.mmCubicos),Volumen,1000000f))

        var unidad1 = uni_list[0]
        var unidad2 = uni_list[0]
        val producto1 = Producto(0F,0F,unidad1)
        val producto2 = Producto(0F,0F,unidad2)

        //creo una val spinner y busco su id
        val sp1 = findViewById<Spinner>(R.id.sp1)
        val sp2 = findViewById<Spinner>(R.id.sp2)
        // los id de las demas cosas..
        val txtprecio1 = findViewById<EditText>(R.id.precio1)
        val txtprecio2 = findViewById<EditText>(R.id.precio2)

        val txtcant1 = findViewById<EditText>(R.id.cantidad1)
        val txtcant2 = findViewById<EditText>(R.id.cantidad2)

        val btnComparar = findViewById<Button>(R.id.btncomparar)
        val txterr = findViewById<TextView>(R.id.txterr)
        val linear1 = findViewById<LinearLayout>(R.id.llopcion1)
        val linear2 = findViewById<LinearLayout>(R.id.llopcion2)


        //creo un ArrayAdapter para la lista creada.
        val adaptador = ArrayAdapter(this,android.R.layout.simple_spinner_item,uni_list)

        // seteo la prop. adapter del spinner con el adaptador creado
        sp1.adapter = adaptador
        sp2.adapter = adaptador

        sp1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener
        {
            override fun onItemSelected(p0: AdapterView<*>?,
                                        p1: View?,
                                        index: Int,
                                        p3: Long) {

                unidad1 = uni_list[index]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                unidad1 = uni_list[0]  // siempre hay algo seleccionado
            }
        }

        sp2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener
        {
            override fun onItemSelected(p0: AdapterView<*>?,
                                        p1: View?,
                                        index: Int,
                                        p3: Long) {

                unidad2 = uni_list[index]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                unidad2 = uni_list[0]  // siempre hay algo seleccionado
            }
        }

        // aqui seteo los eventos ontouch de todos los edit text y spinners
        // para que llamen a la fun "limpiar"

        txtprecio1.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> limpiar(linear1,linear2,txterr)
                }
                return v?.onTouchEvent(event) ?: true }

        })

        txtcant1.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> limpiar(linear1,linear2,txterr)
                }
                return v?.onTouchEvent(event) ?: true }
        })
        txtprecio2.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> limpiar(linear1,linear2,txterr)
                }
                return v?.onTouchEvent(event) ?: true }
        })
        txtcant2.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> limpiar(linear1,linear2,txterr)
                }
                return v?.onTouchEvent(event) ?: true }
        })
        sp1.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> limpiar(linear1,linear2,txterr)
                }
                return v?.onTouchEvent(event) ?: true }
        })
        sp2.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> limpiar(linear1,linear2,txterr)
                }
                return v?.onTouchEvent(event) ?: true }
        })

       btnComparar.setOnClickListener {
           producto1.precio = txtprecio1.text.toString().toFloatOrNull()
           producto1.cantidad = txtcant1.text.toString().toFloatOrNull()
           producto1.uni = unidad1
           producto2.precio = txtprecio2.text.toString().toFloatOrNull()
           producto2.cantidad = txtcant2.text.toString().toFloatOrNull()
           producto2.uni = unidad2

           limpiar(linear1,linear2,txterr)

           txterr.setTextColor(Color.RED)
           txterr.text = vererrores (producto1,producto2)

           if( txterr.text =="" )
           {
               txterr.setTextColor(Color.BLACK)
               when (comparar(producto1, producto2))
                {
                    0 ->  txterr.text = getString(R.string.IgualValor)

                    1 -> { txterr.text = getString(R.string.Prod1Win)
                           pintar(linear1,linear2,1) }

                    2 -> { txterr.text =  getString(R.string.Prod2Win)
                            pintar(linear1, linear2, 2) }
                }
           }
       }
    }
}
