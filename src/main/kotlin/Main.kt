import java.lang.Math.pow
import kotlin.math.sqrt
import kotlin.math.pow

/**
 * Clase Punto
 * Dos constructores, uno principal con el id y otro secundario con las coordenadas
 * @param id es el id que recibirá el punto.
 * @constructor recibe un id, una coordenada X, Int, y una coordenada Y, Int.
 */
class Punto(var id: String) {

    /**
     Declaracion de variables
     */
    private var x: Int = 0
    private var y : Int = 0

    /**
    Constructor Secundario
    */
    constructor(id: String,x: Int, y: Int) : this(id) {
        this.x = x
        this.y = y
    }

    /**
    Función para obtener las coordenadas, devuelve un Pair de dos Int
    */
    fun obtenerCoordenadas(): Pair<Int,Int> {
        return Pair(x,y)
    }

    /**
    Override de la función toString
     */
    override fun toString(): String {
        return "Punto $id -> [$x, $y]"
    }

    /**
     * Métodos de clase
     */
    companion object {

        /**
         * Función componenteDeVector: devuelve otro punto nuevo
         */
        fun componenteDeVector(punto1: Punto, punto2: Punto): Punto {
            return Punto((punto1.id + punto2.id),(punto2.x - punto1.x),(punto2.y - punto1.y))
        }

        /**
        Función distancia: devuelve la distancia entre los dos puntos
        */
        fun distancia(punto1: Punto,punto2: Punto): Double {
            return sqrt((punto2.x - punto1.x).toDouble().pow(2) + (punto2.y - punto1.y).toDouble().pow(2))

        }

        /**
        Función localizaciónGeográficaNS: devuelve un map de String,List<Punto>
                                            que los clasifica en Norte o Sur
        */
        fun localizacionGeograficaNS(lista: Array<Punto>): Map<String,List<Punto>> {
            val listaN: MutableList<Punto> = mutableListOf(Punto(""))
            val listaS: MutableList<Punto> = mutableListOf(Punto(""))
            for (punto in lista) {
                if (punto.y >= 0) {
                    listaN.add(punto)
                } else {
                    listaS.add(punto)
                }
            }
            listaN.removeFirst()
            listaS.removeFirst()
            return mapOf("Norte" to listaN,"Sur" to listaS)
        }

        /**
        Función privada para las potencias, creada porque no existe el operador Potencia
        */
        private fun potencia(x: Int): Int {
            return x * x
        }
    }


}

/**
 * Función para el uso del método estático localizacionGeograficaNS
 */
fun listadoPuntosNorteSur(listado: Array<Punto>): String {
    var lista: String = ""
        listado.forEach { it -> lista += "${it.toString()}, " }
    return "Lista de puntos: $lista \n" +
            "Localizacion Geografica NS: ${Punto.localizacionGeograficaNS(listado)}"

}



fun main() {

    /**
     Declaración de pA y pB
     */
    val pA: Punto = Punto("pA",3,2)
    val pB: Punto = Punto("pB",1,3)


    /**
     * Prueba de Punto.componenteDeVector()
     */
    println(Punto.componenteDeVector(pA,pB))

    /**
     * Prueba de Punto.distancia()
     */
    println(Punto.distancia(pA,pB)) //2.236


    /**
     * Declaración de variables para pruebas
     */
    val p1 = Punto("p1",-1,0)
    val p2 = Punto("p2",3,-1)
    val p3 = Punto("p3",-4,4)
    val p4 = Punto("p4",-3,2)
    val p5 = Punto("p5",6,-4)
    val p6 = Punto("p6",-5,6)
    val p7 = Punto("p7",10,-8)
    val p8 = Punto("p8",1,5)
    val p9 = Punto("p9",6,-7)

    /**
     * Prueba de Punto.localizacionGeograficaNS
     * También se prueba la función creada para el uso de este método estático
     */
    println(listadoPuntosNorteSur(arrayOf(p1,p2,p3,p4,p5,p6,p7,p8,p9)))
}