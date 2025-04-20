package es.iesraprog2425.pruebaes.app

import es.iesraprog2425.pruebaes.data.RepoCalcFich
import es.iesraprog2425.pruebaes.model.Operadores
import es.iesraprog2425.pruebaes.ui.IEntradaSalida
import es.iesraprog2425.pruebaes.utils.IUtilsFicheros
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class Calculadora(private val ui: IEntradaSalida,val fich : IUtilsFicheros,val repo : RepoCalcFich) {

    private fun pedirNumero(msj: String, msjError: String = "Número no válido!"): Double {
        return ui.pedirDouble(msj); throw InfoCalcException(msjError)
    }

    private fun pedirInfo() = Triple(
        pedirNumero("Introduce el primer número: ", "El primer número no es válido!"),
        Operadores.getOperador(ui.pedirInfo("Introduce el operador (+, -, *, /): ").firstOrNull())
            ?: throw InfoCalcException("El operador no es válido!"),
        pedirNumero("Introduce el segundo número: ", "El segundo número no es válido!"))

    private fun realizarCalculo(numero1: Double, operador: Operadores, numero2: Double) =
        when (operador) {
            Operadores.SUMA -> numero1 + numero2
            Operadores.RESTA -> numero1 - numero2
            Operadores.MULTIPLICACION -> numero1 * numero2
            Operadores.DIVISION -> numero1 / numero2
        }

    private fun crearFecha(): String {
        val patron = "yyyyMMddHHmmss"
        val fechaActual = LocalDateTime.now()
        val formateador = DateTimeFormatter.ofPattern(patron)
        return fechaActual.format(formateador)
    }

    fun iniciar() {
        do {
            try {
                ui.limpiarPantalla()
                val (numero1, operador, numero2) = pedirInfo()
                val resultado = realizarCalculo(numero1, operador, numero2 )
                ui.mostrar("Resultado: %.2f".format(resultado))
            } catch (e: NumberFormatException) {
                ui.mostrarError(e.message ?: "Se ha producido un error!")
            }
        } while (ui.preguntar())
        ui.limpiarPantalla()
    }
    fun inicio(args : Array<String>){
        if (args.isEmpty()){
            val ruta = "./log"
            val carpeta = File(ruta)
            if (fich.existeDirectorio(ruta)){
                if (fich.existeFichero(ruta)){
                    val archivos = carpeta.listFiles()?.filter { it.name.matches(Regex("""log\d{14}\.txt""")) }
                    if (!archivos.isNullOrEmpty()) {
                        val masReciente = archivos.maxByOrNull { it.name }!!
                        fich.leerArchivo(masReciente.path)
                    } else {
                        ui.mostrarError("No existen ficheros de log")
                    }
                }
                else{
                    ui.mostrarError("No existen ficheros de log")
                }
            }
            else{
                ui.mostrarError("Error, no existe la carpeta .\\log")
                carpeta.mkdir()
                println("Carpeta \"$ruta\" creada con éxito ")
            }
        }
        if (args.size == 1){
            val ruta = args[0]
            val carpeta = File(ruta)
            if (fich.existeDirectorio(ruta)) {
                val archivos = carpeta.listFiles()?.filter { it.name.matches(Regex("""log\d{14}\.txt""")) }
                if (!archivos.isNullOrEmpty()) {
                    val masReciente = archivos.maxByOrNull { it.name }!!
                    fich.leerArchivo(masReciente.path)
                } else {
                    ui.mostrarError("No existen ficheros de log")
                }
            }
            else{
                ui.mostrar("Error, no existe el archivo")
                carpeta.mkdir()
                println("Carpeta \"$ruta\" creada con éxito ")
            }
        }
        if (args.size == 4){
            val ruta = args[0]
            val carpeta = File(ruta)
            if (fich.existeDirectorio(ruta)){
                try {
                    fich.leerArchivo(ruta)
                    val n1 = args[1].toDouble()
                    val n2 = args[3].toDouble()
                    val operador = Operadores.valueOf(args[2])
                    val resultado = realizarCalculo(n1,operador,n2)
                    ui.mostrar("Resultado: $resultado")
                    val nombreLog = "$ruta/log${crearFecha()}.txt"
                    repo.guardarFicheroLog(nombreLog, "Resultado: $resultado\n")
                }catch (e: Exception){
                    ui.mostrarError("ERROR al procesar la info")
                }
            }
            else{
                ui.mostrar("Error, no existe el archivo")
                carpeta.mkdir()
                println("Carpeta \"$ruta\" creada con éxito ")
            }

        }
        if (args.size > 4){
            ui.mostrarError("**ERROR: POR FAVOR INTRODUZCA EL NUMERO DE ARGUMENTOS NECESARIO**")
        }
    }

}