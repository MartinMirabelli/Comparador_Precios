package prueba

class unidad(val nombre:String,val tipo:TipoUnidad ,val factor:Int) {

}

sealed class TipoUnidad(){
    object Longitud:TipoUnidad()
    object Masa:TipoUnidad()
    object Volumen:TipoUnidad()
}