package com.guevara.pagouniversidad

fun leerObligatorio(mensaje: String): String {
    print(mensaje)
    val valor = readLine()!!
    if (valor.isBlank()) {
        println()
        println("╔══════════════════════════════════════════════╗")
        println("║  ERROR: No se permite dejar el campo vacio.  ║")
        println("║  El programa se detendra.                    ║")
        println("╚══════════════════════════════════════════════╝")
        throw IllegalArgumentException("Entrada vacia no permitida")
    }
    return valor
}

fun leerNumero(mensaje: String): Int {
    val texto = leerObligatorio(mensaje)
    return try {
        texto.toInt()
    } catch (e: NumberFormatException) {
        println()
        println("╔══════════════════════════════════════════════╗")
        println("║  ERROR: Se esperaba un numero entero.        ║")
        println("║  El programa se detendra.                    ║")
        println("╚══════════════════════════════════════════════╝")
        throw IllegalArgumentException("Entrada no numerica")
    }
}

fun leerDecimal(mensaje: String): Double {
    val texto = leerObligatorio(mensaje)
    return try {
        texto.toDouble()
    } catch (e: NumberFormatException) {
        println()
        println("╔══════════════════════════════════════════════╗")
        println("║  ERROR: Se esperaba un numero decimal.       ║")
        println("║  El programa se detendra.                    ║")
        println("╚══════════════════════════════════════════════╝")
        throw IllegalArgumentException("Entrada no numerica")
    }
}

fun leerCategoria(mensaje: String): String {
    val valor = leerObligatorio(mensaje).lowercase()
    if (valor != "ordinario" && valor != "becado") {
        println()
        println("╔══════════════════════════════════════════════╗")
        println("║  ERROR: Categoria invalida.                  ║")
        println("║  Opciones validas: ordinario, becado         ║")
        println("║  El programa se detendra.                    ║")
        println("══════════════════════════════════════════════╝")
        throw IllegalArgumentException("Categoria invalida")
    }
    return valor
}

fun cajaTitulo(texto: String) {
    val borde = "═".repeat(texto.length + 4)
    println("╔$borde╗")
    println("║  $texto  ║")
    println("╚$borde╝")
}

fun cajaSeccion(texto: String) {
    println()
    println("┌${"─".repeat(texto.length + 2)}┐")
    println("│ $texto │")
    println("└${"─".repeat(texto.length + 2)}┘")
}

fun main() {
    println()
    cajaTitulo("SISTEMA DE MATRICULA UNIVERSITARIA")
    println()

    // ---------- AFORO DINAMICO ----------
    cajaSeccion("CONFIGURACION INICIAL")
    val aforoDisponible = leerNumero("Ingrese el aforo disponible en la institucion: ")
    var aforoRestante = aforoDisponible

    while (aforoRestante > 0) {
        println()
        println("╔══════════════════════════════════════════════╗")
        println("║   AFORO DISPONIBLE: %-23d║".format(aforoRestante))
        println("╚══════════════════════════════════════════════╝")

        cajaSeccion("REGISTRO DE ESTUDIANTE")
        val categoria = leerCategoria("Elige tu categoria (ordinario, becado): ")

        if (categoria == "becado") {
            println()
            println("┌──────────────────────────────────────────")
            println("│  Categoria : Becado                      │")
            println("│  Total     : S/0                         │")
            println("└──────────────────────────────────────────┘")
            aforoRestante--
            continue
        }

        // ---------- MONTO INICIAL DE MATRICULA ----------
        val montoInicialMatricula = leerDecimal("Monto inicial de matricula: ")

        val nombreEstudiante = leerObligatorio("Nombre del estudiante: ")
        val cantidadCursos = leerNumero("Cantidad de cursos: ")
        val valorCredito = leerDecimal("Valor de cada credito: ")

        val nombresCursos = mutableListOf<String>()
        val creditosCursos = mutableListOf<Int>()
        val costosCursos = mutableListOf<Double>()

        cajaSeccion("DATOS DE LOS CURSOS")
        for (i in 1..cantidadCursos) {
            println()
            println("  ► Curso $i")
            val nombreCurso = leerObligatorio("    Nombre: ")
            val creditos = leerNumero("    Creditos: ")

            nombresCursos.add(nombreCurso)
            creditosCursos.add(creditos)
            costosCursos.add(creditos * valorCredito)
        }

        var totalCreditos = 0
        var totalPagar = 0.0

        for (i in creditosCursos.indices) {
            totalCreditos += creditosCursos[i]
            totalPagar += costosCursos[i]
        }

        if (totalCreditos > 18) {
            println()
            println("╔══════════════════════════════════════════════════════════╗")
            println("║  ATENCION: La matricula supera los 18 creditos.          ║")
            println("║  Se requiere autorizacion para continuar.                ║")
            println("║  El programa se detendra.                                ")
            println("╚══════════════════════════════════════════════════════════╝")
            return
        }

        cajaSeccion("SELECCION DE TURNO")
        val turno = leerObligatorio("Elige tu turno (mañana, tarde, noche): ").lowercase()

        var porcentajeTurno = 0.0
        if (turno == "mañana") {
            porcentajeTurno = 0.10
        } else if (turno == "tarde") {
            porcentajeTurno = 0.15
        } else if (turno == "noche") {
            porcentajeTurno = 0.20
        }

        val recargoTurno = totalPagar * porcentajeTurno
        totalPagar += recargoTurno

        val igv = totalPagar * 0.18
        val totalConIgv = totalPagar + igv
        val totalFinal = totalConIgv + montoInicialMatricula

        var cargaAcademica = ""
        if (totalCreditos <= 12) {
            cargaAcademica = "M.R"
        } else if (totalCreditos in 13..18) {
            cargaAcademica = "Carga completa"
        } else {
            cargaAcademica = "Requiere autorizacion"
        }

        var numeroCuotas = 0
        if (totalFinal > 2500) {
            numeroCuotas = 3
        } else {
            numeroCuotas = 2
        }
        val valorCuota = totalFinal / numeroCuotas

        // ---------- REPORTE FINAL ----------
        println()
        println("╔══════════════════════════════════════════════════════════════╗")
        println("║                    BOLETA DE MATRICULA                       ║")
        println("╠══════════════════════════════════════════════════════════════╣")
        println("║  Categoria : %-48s║".format("Ordinario"))
        println("║  Estudiante: %-48s║".format(nombreEstudiante))
        println("╠══════════════════════════════════════════════════════════════╣")
        println("║  %-32s %-8s %10s║".format("CURSO", "CRED.", "COSTO"))
        println("══════════════════════════════════════════════════════════════╣")

        for (i in nombresCursos.indices) {
            println("║  %-32s %-8d S/%-8.0f║".format(
                nombresCursos[i].take(32),
                creditosCursos[i],
                costosCursos[i]
            ))
        }

        println("╠══════════════════════════════════════════════════════════════╣")
        println("║  Cursos matriculados   : %-41d║".format(nombresCursos.size))
        println("║  Total de creditos     : %-41d║".format(totalCreditos))
        println("║  Turno                 : %-41s║".format("$turno (recargo ${(porcentajeTurno * 100).toInt()}%)"))
        println("║  Precio inicial matric.: S/%-39.0f║".format(montoInicialMatricula))
        println("║  Subtotal cursos       : S/%-39.0f║".format(totalPagar))
        println("║  IGV (18%%)             : S/%-39.0f║".format(igv))
        println("║  TOTAL A PAGAR         : S/%-39.0f║".format(totalFinal))
        println("║  Cargo academico       : %-41s║".format(cargaAcademica))
        println("║  Forma de pago         : %d cuotas de S/%-24.0f║".format(numeroCuotas, valorCuota))
        println("╚══════════════════════════════════════════════════════════════╝")

        aforoRestante--
        println()
        println("  ✓ Registro completado. Aforo restante: $aforoRestante")
    }

    if (aforoRestante <= 0) {
        println()
        println("╔══════════════════════════════════════════════════════════════╗")
        println("║  AVISO: El aforo esta lleno.                                 ║")
        println("║  No se podrá inscribir más alumnos.                          ║")
        println("╚══════════════════════════════════════════════════════════════╝")
    }
}