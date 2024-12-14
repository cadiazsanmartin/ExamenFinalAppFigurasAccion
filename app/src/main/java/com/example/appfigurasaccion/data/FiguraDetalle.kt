package com.example.appfigurasaccion.data

data class FiguraDetalle(
    val id: Int,
    val nombre: String,
    val logo: String,
    val fechaCreacion: String,
    val origen: String,
    val pelicula: String,
    val descripcion: String,
    val colores: List<String>,
    val disponibilidad: Boolean,
    val precio: Int
)
