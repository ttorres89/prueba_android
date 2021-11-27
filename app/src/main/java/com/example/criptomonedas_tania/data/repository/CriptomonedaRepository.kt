package com.example.criptomonedas_tania.data.repository.dataConversor

import android.util.Log
import com.example.criptomonedas_tania.data.db.config.Criptomoneda_app.Companion.db
import com.example.criptomonedas_tania.data.db.entities.CriptoMonDetailEntity

import com.example.criptomonedas_tania.data.db.entities.CriptomonedaEntity
import com.example.criptomonedas_tania.data.model.Criptomoneda
import com.example.criptomonedas_tania.data.model.CriptomonedaDetail
import com.example.criptomonedas_tania.data.network.CriptomonedaService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CriptomonedaRepository {

    private val criptoService = CriptomonedaService()

    fun getCriptomonedas(): Flow<List<Criptomoneda>> = flow {

        while(true) {
            val criptoResponse = kotlin.runCatching { criptoService.getCriptomonedas() }

            criptoResponse.onSuccess {

                if (it.body() != null) {

                    Log.d("json", it.toString())
                    db.criptomonedaDao()
                        .insertAll(CriptomonedaConversor.convertirCriptoEntity(it.body()!!))
                }
            }

            criptoResponse.onFailure {

                Log.d("ErrorResponse", it.toString())

            }

            val listaCriptoEntity: List<CriptomonedaEntity> = db.criptomonedaDao().getAll()

            if (listaCriptoEntity != null) {

                emit(CriptomonedaConversor.convertirCripto(listaCriptoEntity))
            }
            kotlinx.coroutines.delay(5000)
        }
    }.flowOn(Dispatchers.IO)


    fun getCriptomoneda(id:String): Flow<CriptomonedaDetail> = flow{

        while(true) {
            val booksResponse = kotlin.runCatching { criptoService.getCriptomoneda(id) }

            booksResponse.onSuccess {
                if (it.body() != null) {
                    Log.d("libro_selec", it.body().toString())
                    db.criptomonedaDao().insertCriptomoneda(CriptomonedaConversor.convertirCriptoDetailEntity(it.body()!!))

                }
            }
            booksResponse.onFailure {
                Log.d("Error_unique_cripto", it.toString())

            }

            val criptoDetailEntity: CriptoMonDetailEntity = db.criptomonedaDao().getCriptomoneda(id)

            if (criptoDetailEntity != null) {
                emit(CriptomonedaConversor.convertCriptoDetail(criptoDetailEntity))
            }
            kotlinx.coroutines.delay(5000)
        }

    }.flowOn(Dispatchers.IO)

}