package com.example.criptomonedas_tania

import androidx.lifecycle.asLiveData
import com.example.criptomonedas_tania.data.model.Criptomoneda
import com.example.criptomonedas_tania.data.model.CriptomonedaDetail
import com.example.criptomonedas_tania.data.repository.dataConversor.CriptomonedaRepository
import com.example.criptomonedas_tania.ui.viewmodel.CriptomonedaViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class CriptomonedaViewModelTest {

    @get:Rule
    val coroutinesTestRule = TestRuleCoroutines()

    @ExperimentalCoroutinesApi
    @Test
    fun getCriptomonedas () = runBlockingTest {

        val mainViewModel = CriptomonedaViewModel()
        val repository = CriptomonedaRepository()

        val criptomonedaDetail = CriptomonedaDetail(
            "BTC",
        "BTC",
        "BTC",
        "Bitcoin",
        "https://s3.us-east-2.amazonaws.com/nomics-api/static/images/currencies/btc.svg",
        "active",
        57908.88281873,
        "2021-05-08T00:00:00Z",
        "2021-05-08T06:15:00Z",
        18703231,
        21000000,
            384,
            60151,
            5065,
        "2011-08-18T00:00:00Z",
            "2011-08-18T00:00:00Z",
        "2017-01-06T00:00:00Z",
         1,
            0,
            63512.44673149,
         "2021-04-13T00:00:00Z")


        val idCripto= "BTC"
        val criptoDetail = repository.getCriptomoneda(idCripto).asLiveData()
        Assert.assertEquals(criptomonedaDetail.name, criptoDetail.value!!.name)


    }

}