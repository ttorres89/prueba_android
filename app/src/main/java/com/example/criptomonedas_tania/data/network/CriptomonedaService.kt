package com.example.criptomonedas_tania.data.network

import com.example.criptomonedas_tania.data.model.Criptomoneda
import com.example.criptomonedas_tania.data.model.CriptomonedaDetail
import com.example.criptomonedas_tania.data.network.core.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class CriptomonedaService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getCriptomonedas(): Response<List<Criptomoneda>> {
        return withContext(Dispatchers.IO){
            retrofit.create(ApiServices::class.java).getAll()
        }
    }
    suspend fun getCriptomoneda(id:String): Response<CriptomonedaDetail> {
        return withContext(Dispatchers.IO){
            retrofit.create(ApiServices::class.java).getCriptomoneda(id)
        }
    }

}