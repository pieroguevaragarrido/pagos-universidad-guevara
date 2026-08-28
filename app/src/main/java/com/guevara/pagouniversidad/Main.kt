package com.guevara.pagouniversidad

fun main() {
    println("Nombre del estudiante:")
    val nombreEstudiante = readLine()!!

    println("Cantidad de cursos:")
    val cantidadCursos = readLine()!!.toInt()

    println("Valor de cada credito:")
    val valorCredito = readLine()!!.toDouble()

    val nombresCursos = mutableListOf<String>()
    val creditosCursos = mutableListOf<Int>()
    val costosCursos = mutableListOf<Double>()

    var i = 1
    while (i <= cantidadCursos) {
        println("Curso $i:")
        val nombreCurso = readLine()!!

        println("Creditos:")
        val creditos = readLine()!!.toInt()

        val costoCurso = creditos * valorCredito

        nombresCursos.add(nombreCurso)
        creditosCursos.add(creditos)
        costosCursos.add(costoCurso)

        i++
    }

    var totalCreditos = 0
    var totalPagar = 0.0

    var j = 0
    while (j < creditosCursos.size) {
        totalCreditos += creditosCursos[j]
        totalPagar += costosCursos[j]
        j++
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

    println()
    println("--- Calculos realizados ---")
    println("Total de creditos: $totalCreditos")
    println("Total a pagar: S/$totalPagar")
    println("Carga academica: $cargaAcademica")
    println("Numero de cuotas: $numeroCuotas")
    println("Valor de cada cuota: S/$valorCuota")
}