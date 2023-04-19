package com.example.comparadorpreciosmirabelli

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import prueba.Producto
import prueba.TipoUnidad.*
import prueba.unidad

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // cargo lista de tipo unidad con las unidades y caracteristicas de cada una...
        val uni_list = listOf<unidad>(unidad("Metros (m)",Longitud,1 ),
            unidad("Centimetros (cm)",Longitud,100 ),
            unidad("Milimetros (mm)",Longitud,1000),
            unidad("Kilogramos (Kg)",Masa,1),
            unidad("Gramos (gr)",Masa,1000),
            unidad("Litros (L)",Volumen,1),
            unidad("Centilitros (cL)",Volumen,100),
            unidad("Centimetros Cubico (cm3)",Volumen,1000),
            unidad("Milimetros Cubicos (mm3)",Volumen,1000000))

        var unidad1 = uni_list[0]
        var unidad2 = uni_list[0]
        val producto1 = Producto(0.toFloat(),0.toFloat(),unidad1)
        val producto2 = Producto(0.toFloat(),0.toFloat(),unidad2)

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
               //Toast.makeText(this@MainActivity,uni_list[index].factor.toString(),Toast.LENGTH_SHORT).show()
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

                //Toast.makeText(this@MainActivity,uni_list[index].factor.toString(),Toast.LENGTH_SHORT).show()
                unidad2 = uni_list[index]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                unidad2 = uni_list[0]  // siempre hay algo seleccionado
            }
        }

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
                    0 ->  txterr.text = "Ambos Productos valen lo mismo"

                    1 -> { txterr.text = "El producto 1 es al mas Economico"
                           pintar(linear1,linear2,1) }

                    2 -> { txterr.text = "El producto 2 es al mas Economico"
                            pintar(linear1, linear2, 2) }
                }
           }
       }
    }
}

fun vererrores (p1:Producto, p2:Producto):String {
    if (p1.uni.tipo != p2.uni.tipo)
        return p1.uni.toString() + " No es Comparable con " + p2.uni.toString()

    if (p1.cantidad == null || p2.cantidad == null || p1.precio == null || p2.precio== null)
        return "Debe especificar TODOS los precios cantidades"
    else
    {
        return ""
    }
}
fun comparar (p1:Producto , p2:Producto):Int {

    val precioxunidad1 = p1.precio!! * p1.uni.factor / p1.cantidad!!
    val precioxunidad2 = p2.precio!! * p2.uni.factor / p2.cantidad!!

    if (precioxunidad1 < precioxunidad2) return 1
    else if(precioxunidad1 > precioxunidad2) return 2
    else  return 0
}

fun limpiar( ll1:LinearLayout,ll2:LinearLayout,txt:TextView )
{
    ll1.setBackgroundColor(Color.TRANSPARENT)
    ll2.setBackgroundColor(Color.TRANSPARENT)
    txt.text=""
}
fun pintar( ll1:LinearLayout,ll2:LinearLayout, cual:Int )
{
    when(cual)
    {
        1->ll1.setBackgroundColor(Color.GREEN)
        2->ll2.setBackgroundColor(Color.GREEN)
    }

}