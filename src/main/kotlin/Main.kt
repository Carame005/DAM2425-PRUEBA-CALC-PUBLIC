package es.iesraprog2425.pruebaes

import es.iesraprog2425.pruebaes.app.Calculadora
import es.iesraprog2425.pruebaes.ui.Consola
import es.iesraprog2425.pruebaes.ui.IEntradaSalida
import es.iesraprog2425.pruebaes.utils.IUtilsFicheros
import java.io.File

fun main(args : Array<String>) {
val fich : IUtilsFicheros
val ui : IEntradaSalida = Consola()
val log = "src/main/kotlin/log"
val directorio = File("./log")
    Calculadora(Consola()).iniciar()
}

