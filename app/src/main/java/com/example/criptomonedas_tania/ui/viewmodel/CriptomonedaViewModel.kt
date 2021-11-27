package com.example.criptomonedas_tania.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.criptomonedas_tania.data.db.config.Criptomoneda_app.Companion.prefs
import com.example.criptomonedas_tania.data.repository.dataConversor.CriptomonedaRepository

class CriptomonedaViewModel  : ViewModel() {

    private val repository = CriptomonedaRepository()

    val criptomonedas = repository.getCriptomonedas().asLiveData()
    var detail_criptomoneda=  repository.getCriptomoneda(prefs.idCripto!!).asLiveData()

}