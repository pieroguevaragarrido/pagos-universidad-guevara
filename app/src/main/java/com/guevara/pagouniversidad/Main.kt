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

    var i = 1
    while (i <= cantidadCursos) {
        println("Curso $i:")
        val nombreCurso = readLine()!!

        println("Creditos:")
        val creditos = readLine()!!.toInt()

        nombresCursos.add(nombreCurso)
        creditosCursos.add(creditos)

        i++
    }

    // Impresion temporal solo para confirmar que el input se capturo bien
    println()
    println("--- Datos capturados ---")
    println("Estudiante: $nombreEstudiante")
    println("Cantidad de cursos: $cantidadCursos")
    println("Valor por credito: S/$valorCredito")

    var j = 0
    while (j < nombresCursos.size) {
        println("Curso: ${nombresCursos[j]} - Creditos: ${creditosCursos[j]}")
        j++
    }
}