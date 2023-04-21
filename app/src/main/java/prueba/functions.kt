package prueba

import android.graphics.Color
import android.widget.LinearLayout
import android.widget.TextView

//------------------------------------------------------------------------------
// esta fun verifica si hay errores
// si los hay devulve un string con la descripcion del error
// si no hay errores devuelve un string vacio ""
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

//---------------------------------------------------------------------
//  esta func compara los valores obteniedo el precio por unidad
// de cada uno.
// retorna:
// 0 -> si tiene en mismo valor
// 1 -> cuando el 1 es el mas barato
// 2 -> cuando el 2 es el mas barato
fun comparar (p1:Producto , p2:Producto):Int {

    val precioxunidad1 = p1.precio!! * p1.uni.factor / p1.cantidad!!
    val precioxunidad2 = p2.precio!! * p2.uni.factor / p2.cantidad!!

    if (precioxunidad1 < precioxunidad2) return 1
    else if(precioxunidad1 > precioxunidad2) return 2
    else  return 0
}
//----------------------------------------------------------------------
// limpia el background de los 2 linearlayout y los mensajes de error
fun limpiar(ll1: LinearLayout, ll2: LinearLayout, txt: TextView)
{
    ll1.setBackgroundColor(Color.TRANSPARENT)
    ll2.setBackgroundColor(Color.TRANSPARENT)
    txt.text=""
}
//----------------------------------------------------------------------
// pinta de verde el layout del producto ganador...
fun pintar(ll1: LinearLayout, ll2: LinearLayout, cual:Int )
{
    when(cual)
    {
        1->ll1.setBackgroundColor(Color.GREEN)
        2->ll2.setBackgroundColor(Color.GREEN)
    }
}