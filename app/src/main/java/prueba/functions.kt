package prueba

import android.graphics.Color
import android.widget.LinearLayout
import android.widget.TextView

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