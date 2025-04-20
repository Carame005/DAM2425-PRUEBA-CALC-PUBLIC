package es.iesraprog2425.pruebaes.data

import java.io.File

class RepoCalcFich : IRepoCalclFich {
    override fun guardarFicheroLog(nombreArchivo: String, contenido: String) {
        val archivo = File(nombreArchivo)
        archivo.writeText(contenido) // o appendText si quieres agregar sin sobrescribir
    }

}