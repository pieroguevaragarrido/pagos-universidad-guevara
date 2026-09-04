package com.guevara.pagouniversidad

fun main() {
    println("Elige tu categoria (ordinario, becado):")
    val categoria = readLine()!!.lowercase()

    if (categoria == "becado") {
        println()
        println("Categoria: Becado")
        println("Total a pagar: S/0")
        return
    }

    println("Nombre del estudiante:")
    val nombreEstudiante = readLine()!!

    println("Cantidad de cursos:")
    val cantidadCursos = readLine()!!.toInt()

    println("Valor de cada credito:")
    val valorCredito = readLine()!!.toDouble()

    val nombresCursos = mutableListOf<String>()
    val creditosCursos = mutableListOf<Int>()
    val costosCursos = mutableListOf<Double>()

    for (i in 1..cantidadCursos) {
        println("Curso $i:")
        val nombreCurso = readLine()!!

        println("Creditos:")
        val creditos = readLine()!!.toInt()

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
        println("Estudiante: $nombreEstudiante")
        println("Total de creditos: $totalCreditos")
        println()
        println("ATENCION: La matricula supera los 18 creditos.")
        println("Se requiere autorizacion para continuar con el proceso de pago.")
        println("El programa se detendra.")
        return
    }

    println()
    println("Elige tu turno (mañana, tarde, noche):")
    val turno = readLine()!!.lowercase()

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

    // ---------- IGV ----------
    val igv = totalPagar * 0.18
    val totalConIgv = totalPagar + igv

    var cargaAcademica = ""
    if (totalCreditos <= 12) {
        cargaAcademica = "M.R"
    } else if (totalCreditos in 13..18) {
        cargaAcademica = "Carga completa"
    } else {
        cargaAcademica = "Requiere autorizacion"
    }

    var numeroCuotas = 0
    if (totalConIgv > 2500) {
        numeroCuotas = 3
    } else {
        numeroCuotas = 2
    }
    val valorCuota = totalConIgv / numeroCuotas

    println()
    println("Categoria: Ordinario")
    println("Estudiante: $nombreEstudiante")
    println()
    println(String.format("%-30s %-10s %s", "Cursos:", "Creditos", "Costo"))

    for (i in nombresCursos.indices) {
        println(
            String.format(
                "%-30s %-10d S/%.0f",
                nombresCursos[i], creditosCursos[i], costosCursos[i]
            )
        )
    }

    println()
    println("Cursos matriculados: ${nombresCursos.size}")
    println("Total de creditos: $totalCreditos")
    println("Turno: $turno (recargo ${(porcentajeTurno * 100).toInt()}%)")
    println(String.format("Subtotal (con turno): S/%.0f", totalPagar))
    println(String.format("IGV (18%%): S/%.0f", igv))
    println(String.format("Total a pagar (con IGV): S/%.0f", totalConIgv))
    println("Cargo academico: $cargaAcademica")
    println(String.format("Forma de pago: %d cuotas de S/%.0f", numeroCuotas, valorCuota))
}