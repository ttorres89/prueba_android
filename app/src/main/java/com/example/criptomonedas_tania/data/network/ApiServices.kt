package com.example.criptomonedas_tania.data.network

import com.example.criptomonedas_tania.data.model.Criptomoneda
import com.example.criptomonedas_tania.data.model.CriptomonedaDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {

    @GET("general/")
    suspend fun getAll(): Response<List<Criptomoneda>>

    @GET("details/{id}")
    suspend fun getCriptomoneda(@Path("id")  id:String): Response<CriptomonedaDetail>

}