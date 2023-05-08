package prueba

// clase unidad para definir las unidades utilizadas en la aplicacion

open class unidad(val nombre:String,val tipo:TipoUnidad ,val factor:Float) {

    override fun toString(): String {
        return nombre
    }
}

// clase sealed para definir los TIPOS de unidades utilizadas en la aplicacion y
// poder saber si son compatibles o no.
// longitud, masa y volumen.
sealed class TipoUnidad(){
    object Longitud:TipoUnidad()
    object Masa:TipoUnidad()
    object Volumen:TipoUnidad()
}
class Producto (var precio:Float?, var cantidad:Float? , var uni: unidad){

    // esto calcula el precio x unidad de un producto
    fun PrecioXunidad(): Float{
        return precio!! * uni.factor / cantidad!!
    }
    //  esta func compara los valores obteniedo el precio por unidad
    // de cada uno.
    // retorna:
    // 0 -> si tiene en mismo valor
    // 1 -> cuando el 1 es el mas barato
    // 2 -> cuando el 2 es el mas barato
    companion object {
        fun comparar(p1: Producto, p2: Producto): Int {

            if (p1.PrecioXunidad() < p2.PrecioXunidad()) return 1
            else if (p1.PrecioXunidad() > p2.PrecioXunidad()) return 2
            else return 0
        }

        //------------------------------------------------------------------------------
        // esta fun verifica si hay errores
        // si los hay devulve un string con la descripcion del error
        // si no hay errores devuelve un string vacio ""
        fun vererrores (p1:Producto, p2:Producto):String {
            if (p1.uni.tipo != p2.uni.tipo)
                return p1.uni.toString() + " No es Comparable con " + p2.uni.toString()

            if (p1.cantidad == null || p2.cantidad == null || p1.precio == null || p2.precio== null)
                return "Debe especificar TODOS los precios y cantidades"
            else if (p1.cantidad == 0F || p2.cantidad == 0F || p1.precio == 0F || p2.precio== 0F)
                return "Los Precios y Cantidades NO pueden ser 0 (cero)"
            else
                return ""  // no hay errores

        }
    }
}
