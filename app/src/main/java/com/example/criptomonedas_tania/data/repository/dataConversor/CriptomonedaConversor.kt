package com.example.criptomonedas_tania.data.repository.dataConversor

import com.example.criptomonedas_tania.data.db.entities.CriptoMonDetailEntity
import com.example.criptomonedas_tania.data.db.entities.CriptomonedaEntity
import com.example.criptomonedas_tania.data.model.Criptomoneda
import com.example.criptomonedas_tania.data.model.CriptomonedaDetail

class CriptomonedaConversor {

    companion object
    {
        fun convertirCriptoEntity(criptomoneda: List<Criptomoneda>): List<CriptomonedaEntity>
        {
            val criptoEntitys = mutableListOf<CriptomonedaEntity>()

            criptomoneda.map{
                criptoEntitys.add(CriptomonedaEntity(it.id, it.currency, it.symbol, it.name, it.logo_url, it.status, it.price, it.price_date, it.price_timestamp, it.rank))
            }

            return criptoEntitys
        }

        fun convertirCripto (criptomonedaEntity: List<CriptomonedaEntity>): List<Criptomoneda>{

            val criptomonedas = mutableListOf<Criptomoneda>()

            criptomonedaEntity.map {
                criptomonedas.add(Criptomoneda(it.id, it.currency, it.symbol, it.name, it.logo_url, it.status, it.price, it.price_date, it.price_timestamp, it.rank))
            }

            return criptomonedas
        }


        fun convertirCriptoDetailEntity(criptoDeail: CriptomonedaDetail): CriptoMonDetailEntity {

            return CriptoMonDetailEntity(criptoDeail.id, criptoDeail.currency, criptoDeail.symbol, criptoDeail.name, criptoDeail.logo_url, criptoDeail.status, criptoDeail.price, criptoDeail.price_date, criptoDeail.price_timestamp, criptoDeail.circulating_supply, criptoDeail.market_cap, criptoDeail.num_exchanges, criptoDeail.num_pairs, criptoDeail.num_pairs_unmapped, criptoDeail.first_candle, criptoDeail.first_trade, criptoDeail.first_order_book, criptoDeail.rank,criptoDeail.rank_delta, criptoDeail.high, criptoDeail.high_timestamp)
        }

        fun convertCriptoDetail(criptoMonedaDetailEntity: CriptoMonDetailEntity): CriptomonedaDetail {


            return CriptomonedaDetail(criptoMonedaDetailEntity.id, criptoMonedaDetailEntity.currency, criptoMonedaDetailEntity.symbol, criptoMonedaDetailEntity.name, criptoMonedaDetailEntity.logo_url, criptoMonedaDetailEntity.status, criptoMonedaDetailEntity.price, criptoMonedaDetailEntity.price_date, criptoMonedaDetailEntity.price_timestamp, criptoMonedaDetailEntity.circulating_supply, criptoMonedaDetailEntity.market_cap, criptoMonedaDetailEntity.num_exchanges, criptoMonedaDetailEntity.num_pairs, criptoMonedaDetailEntity.num_pairs_unmapped, criptoMonedaDetailEntity.first_candle, criptoMonedaDetailEntity.first_trade, criptoMonedaDetailEntity.first_order_book, criptoMonedaDetailEntity.rank, criptoMonedaDetailEntity.rank_delta, criptoMonedaDetailEntity.high, criptoMonedaDetailEntity.high_timestamp)

        }


    }


}