package es.iesraprog2425.pruebaes

import es.iesraprog2425.pruebaes.app.Calculadora
import es.iesraprog2425.pruebaes.ui.Consola
import es.iesraprog2425.pruebaes.utils.IUtilsFicheros
import java.io.File

fun main(fich : IUtilsFicheros) {

val log = "./log"
if (fich.existeFichero(log)){
    val f = File(log)
}else{
    val mkdir = Mkdir("./log")
}

    Calculadora(Consola()).iniciar()
}

