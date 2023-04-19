package prueba

// clase unidad para definir las unidades utilizadas en la aplicacion

open class unidad(val nombre:String,val tipo:TipoUnidad ,val factor:Int) {

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
class Producto (var precio:Float?, var cantidad:Float? , var uni: unidad)
