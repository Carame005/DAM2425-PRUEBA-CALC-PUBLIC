package es.iesraprog2425.pruebaes.data
import java.io.File

interface IRepoCalclFich {
    fun guardarFicheroLog(nombreArchivo: String, contenido: String)
}