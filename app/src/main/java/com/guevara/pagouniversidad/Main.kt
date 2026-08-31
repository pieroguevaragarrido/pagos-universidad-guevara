package com.guevara.pagouniversidad

fun main() {
    println("Nombre del estudiante:")
    val nombreEstudiante = readLine()!!

    println("Cantidad de cursos:")
    val cantidadCursos = readLine()!!.toInt()

    println("Valor de cada credito:")
    val valorCredito = readLine()!!.toDouble()

    val nombresCursos = arrayOfNulls<String>(cantidadCursos)
    val creditosCursos = IntArray(cantidadCursos)
    val costosCursos = DoubleArray(cantidadCursos)

    for (i in 0 until cantidadCursos) {
        println("Curso ${i + 1}:")
        val nombreCurso = readLine()!!

        println("Creditos:")
        val creditos = readLine()!!.toInt()

        nombresCursos[i] = nombreCurso
        creditosCursos[i] = creditos
        costosCursos[i] = creditos * valorCredito
    }

    var totalCreditos = 0
    var totalPagar = 0.0

    for (i in 0 until cantidadCursos) {
        totalCreditos += creditosCursos[i]
        totalPagar += costosCursos[i]
    }

    // ---------- VALIDACION: mas de 18 creditos requiere autorizacion ----------
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

    var cargaAcademica = ""
    if (totalCreditos <= 12) {
        cargaAcademica = "M.R"
    } else if (totalCreditos in 13..18) {
        cargaAcademica = "Carga completa"
    } else {
        cargaAcademica = "Requiere autorizacion"
    }

    var numeroCuotas = 0
    if (totalPagar > 2500) {
        numeroCuotas = 3
    } else {
        numeroCuotas = 2
    }
    val valorCuota = totalPagar / numeroCuotas

    // ---------- RESULTADO FINAL ----------
    println()
    println("Estudiante: $nombreEstudiante")
    println()
    println(String.format("%-30s %-10s %s", "Cursos:", "Creditos", "Costo"))

    for (i in 0 until cantidadCursos) {
        println(
            String.format(
                "%-30s %-10d S/%.0f",
                nombresCursos[i], creditosCursos[i], costosCursos[i]
            )
        )
    }

    println()
    println("Cursos matriculados: $cantidadCursos")
    println("Total de creditos: $totalCreditos")
    println(String.format("Total a pagar: S/%.0f", totalPagar))
    println("Cargo academico: $cargaAcademica")
    println(String.format("Forma de pago: %d cuotas de S/%.0f", numeroCuotas, valorCuota))
}