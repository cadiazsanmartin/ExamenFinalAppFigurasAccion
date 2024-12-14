package com.example.appfigurasaccion.viewmodel

import android.app.Application
import android.telecom.Call
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.tracing.perfetto.handshake.protocol.Response
import com.example.appfigurasaccion.data.FiguraDetalle
import com.example.appfigurasaccion.network.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Callback
import retrofit2.Response

class FiguraViewModel(application: Application) : AndroidViewModel(application) {

    val figuraDetalle = MutableLiveData<FiguraDetalle>()
    val errorMessage = MutableLiveData<String>()

    // Cargar el detalle de una figura
    fun getFiguraDetalle(id: Int) {
        viewModelScope.launch {
            RetrofitInstance.apiService.getFiguraDetalle(id).enqueue(object : Callback<FiguraDetalle> {
                override fun onResponse(call: Call<FiguraDetalle>, response: Response<FiguraDetalle>) {
                    if (response.isSuccessful) {
                        figuraDetalle.postValue(response.body())
                    } else {
                        errorMessage.postValue("Error al cargar el detalle de la figura")
                    }
                }

                override fun onFailure(call: Call<FiguraDetalle>, t: Throwable) {
                    errorMessage.postValue(t.message)
                }
            })
        }
    }
}