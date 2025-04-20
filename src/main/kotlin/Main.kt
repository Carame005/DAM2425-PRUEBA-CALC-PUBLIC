package es.iesraprog2425.pruebaes

import es.iesraprog2425.pruebaes.app.Calculadora
import es.iesraprog2425.pruebaes.data.RepoCalcFich
import es.iesraprog2425.pruebaes.ui.Consola
import es.iesraprog2425.pruebaes.utils.FicherosTexto


fun main(args: Array<String>) {
    Calculadora(Consola(), FicherosTexto(Consola()), RepoCalcFich()).inicio(args)
}

