package es.iesraprog2425.pruebaes.utils


import es.iesraprog2425.pruebaes.ui.IEntradaSalida
import java.io.File
import java.io.IOException

class FicherosTexto(private val ui : IEntradaSalida) : IUtilsFicheros {

    override fun leerArchivo(ruta: String): List<String> {
        val archivo = File(ruta)
        return try {
            if (!archivo.exists()){
                ui.mostrarError("No fue posible leer el archivo \"$ruta\" porque no existe")
                return emptyList()
            }
            archivo.readLines()
        }catch (e: IOException){
            ui.mostrarError("No fue posible leer el archivo \"$ruta\" porque no existe")
            emptyList()
        }catch (e: Exception){
            ui.mostrarError("Se produjo una excepion")
            emptyList()
        }
    }

    override fun agregarLinea(ruta: String, linea: String): Boolean {
        val archivo = File(ruta)
        try {
            archivo.appendText(linea)
            return true
        }catch (e: IOException){
            ui.mostrarError("No fue posible leer el archivo \"$ruta\" porque no existe")
        }catch (e: Exception){
            ui.mostrarError("Se produjo una excepion")
        }
        return false
    }


    override fun existeFichero(ruta: String): Boolean {
        return File(ruta).exists()
    }

    override fun existeDirectorio(ruta: String): Boolean {
        return File(ruta).isDirectory
    }
}