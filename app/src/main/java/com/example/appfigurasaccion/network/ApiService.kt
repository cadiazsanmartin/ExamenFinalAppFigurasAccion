package com.example.appfigurasaccion.network

import android.telecom.Call
import com.example.appfigurasaccion.data.Figura
import com.example.appfigurasaccion.data.FiguraDetalle
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    // Endpoint para obtener la lista de figuras
    @GET("figuras")
    fun getFiguras(): Call<List<Figura>>

    // Endpoint para obtener el detalle de una figura
    @GET("figuras/{id}")
    fun getFiguraDetalle(@Path("id") id: Int): Call<FiguraDetalle>
}